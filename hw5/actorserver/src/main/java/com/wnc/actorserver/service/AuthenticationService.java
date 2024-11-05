package com.wnc.actorserver.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.wnc.actorserver.dto.request.AuthenticationRequest;
import com.wnc.actorserver.dto.request.IntrospectRequest;
import com.wnc.actorserver.dto.request.LogoutRequest;
import com.wnc.actorserver.dto.request.RefreshRequest;
import com.wnc.actorserver.dto.response.AuthenticationResponse;
import com.wnc.actorserver.dto.response.IntrospectResponse;
import com.wnc.actorserver.exception.AppException;
import com.wnc.actorserver.exception.ErrorCode;
import com.wnc.actorserver.model.RefreshToken;
import com.wnc.actorserver.model.User;
import com.wnc.actorserver.repository.RefreshTokenRepository;
import com.wnc.actorserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    RefreshTokenRepository refreshTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    // adding error unauthenticated and user not exist
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_INVALID));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(user, false);
        var refreshToken = generateToken(user, true);
        System.out.println(refreshToken);

        RefreshToken refreshTokenRecord = RefreshToken.builder().token(refreshToken).expiryTime(new Date(
                Instant.now().plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())).build();
        refreshTokenRepository.save(refreshTokenRecord);

        return AuthenticationResponse.builder().token(token).refreshToken(refreshToken).authenticated(true).build();
    }

    @Transactional
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var refreshToken = request.getToken();

            refreshTokenRepository.deleteByToken(refreshToken);
        } catch (AppException exception) {
            //log.info("Token already expired");
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var refreshToken = request.getRefreshToken();
        refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var signedJWT = verifyToken(refreshToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var newAccessToken = generateToken(user, false);

        return AuthenticationResponse.builder().token(newAccessToken).refreshToken(refreshToken).authenticated(true).build();
    }

    private String generateToken(User user, boolean isRefresh) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        var timeToLive = isRefresh ? REFRESHABLE_DURATION : VALID_DURATION;

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("wnc.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(timeToLive, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }


    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.USERNAME_INVALID);

        return signedJWT;
    }
}
