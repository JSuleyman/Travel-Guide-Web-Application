package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "email_verification")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserEmailVerification extends CoreEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    String id;

    String verificationCode;
    LocalDateTime verificationCodeCreatedAt;
    int verificationCodeExpirationMinutes;
    boolean hasExpired;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    User user;
}
