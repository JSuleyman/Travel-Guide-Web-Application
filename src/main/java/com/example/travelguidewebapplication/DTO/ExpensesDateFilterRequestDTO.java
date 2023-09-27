package com.example.travelguidewebapplication.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpensesDateFilterRequestDTO {
    LocalDateTime startDate;
    LocalDateTime endDate;
}
