package com.wnc.actorserver.service;

import com.wnc.actorserver.dto.request.UserCreationRequest;
import com.wnc.actorserver.exception.AppException;
import com.wnc.actorserver.exception.ErrorCode;
import com.wnc.actorserver.model.User;
import com.wnc.actorserver.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository  userRepository;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return newUser;
    }
}
