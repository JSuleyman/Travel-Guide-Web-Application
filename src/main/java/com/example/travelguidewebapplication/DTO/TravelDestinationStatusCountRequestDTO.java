package com.example.travelguidewebapplication.DTO;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelDestinationStatusCountRequestDTO {
    Integer pendingCount;
    Integer approvedCount;
    Integer completedCount;
    Integer allCount;
}
