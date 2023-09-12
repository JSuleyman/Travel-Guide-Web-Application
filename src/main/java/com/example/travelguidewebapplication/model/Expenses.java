package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cost_list")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Expenses extends CoreEntity {
    String costDescription;
    Double cost;
    String status;
    LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User userId;

//    @ManyToOne
//    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false)
//    Currency currencyId;
}
