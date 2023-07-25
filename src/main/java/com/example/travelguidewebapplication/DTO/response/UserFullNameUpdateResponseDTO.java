package com.example.travelguidewebapplication.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserFullNameUpdateResponseDTO {
    String firstName;
    String lastName;
}
