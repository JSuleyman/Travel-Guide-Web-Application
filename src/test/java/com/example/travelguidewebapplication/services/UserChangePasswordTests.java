package com.example.travelguidewebapplication.services;

import com.example.travelguidewebapplication.model.User;
import com.example.travelguidewebapplication.repository.UserRespository;
import com.example.travelguidewebapplication.security.AuthenticationService;
import com.example.travelguidewebapplication.security.UserChangePasswordRequestDTO;
import com.example.travelguidewebapplication.service.inter.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserChangePasswordTests {
    @Mock
    private UserService userService;

    @Mock
    private UserRespository userRespository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testChangeUserPassword_Successful() {
        //Given
        User user = User.builder()
                .id(1)
                .email("test@test")
                .password("1234")
                .build();

        UserChangePasswordRequestDTO userChangePasswordRequestDTO = UserChangePasswordRequestDTO.builder()
                .oldPassword("1234")
                .newPassword("12345")
                .repeatPassword("12345")
                .build();

        // When: UserService.getCurrentUser() method is called
        when(userService.getCurrentUser()).thenReturn(user);

        // When: UserRepository.save() is called to save the updated user
        when(userRespository.save(any())).thenReturn(user);

        // When: PasswordEncoder.matches() is called to check the old password1
        when(passwordEncoder.matches("1234", "1234")).thenReturn(true);

        // When: PasswordEncoder.encode() is called to encode the new password
        when(passwordEncoder.encode(any())).thenReturn(userChangePasswordRequestDTO.getNewPassword());

        // When: The changeUserPassword() method is called
        String result = authenticationService.changeUserPassword(userChangePasswordRequestDTO);

        // Then: The result should indicate a successful password change
        boolean resultForTure = result.equals("Parol ugurla deyisdirildi!");
        assertTrue(resultForTure);

        // Then: The user's password should be updated to the new password
        assertEquals(user.getPassword(), "12345");
    }
    // other if
}
