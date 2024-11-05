package com.wnc.actorserver.model;

import java.util.Date;

import jakarta.persistence.*;

import lombok.*;
        import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(name="token", length = 512)
    String token;
    Date expiryTime;
}