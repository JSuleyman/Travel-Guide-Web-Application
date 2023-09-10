package com.example.travelguidewebapplication.model;


import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "wallet_by_user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"fk_user_id"})})
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Wallet extends CoreEntity {
    Double totalMoney;
    Double moneyLeft;

    @OneToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id", nullable = false)
    User user;
}
