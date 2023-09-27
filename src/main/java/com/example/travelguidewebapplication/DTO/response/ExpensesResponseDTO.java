package com.example.travelguidewebapplication.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponseDTO {
    String costDescription;
    Double cost;
    //    String currency;
    String id;
    LocalDateTime localDateTime;
    boolean isHaveImage;
}
