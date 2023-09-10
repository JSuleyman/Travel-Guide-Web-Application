package com.example.travelguidewebapplication.DTO.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class WalletTotalMonetResponseDTO {
    Double totalMoney;
    Double moneyLeft;
    boolean isHaveTotalMoney;
}
