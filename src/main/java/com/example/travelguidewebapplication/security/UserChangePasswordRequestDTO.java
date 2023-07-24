package com.example.travelguidewebapplication.security;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChangePasswordRequestDTO {
    String oldPassword;
    String newPassword;
    String repeatPassword;
}
