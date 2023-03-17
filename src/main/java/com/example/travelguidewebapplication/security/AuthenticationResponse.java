package com.example.travelguidewebapplication.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String message;

    public AuthenticationResponse(String meessage) {
        this.message = meessage;
    }

//    public AuthenticationResponse(String message){
//        this.message = message;
//    }
}
