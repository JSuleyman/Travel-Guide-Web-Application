package com.example.travelguidewebapplication.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDTO {
    String destinationName;

    String comment;
}
