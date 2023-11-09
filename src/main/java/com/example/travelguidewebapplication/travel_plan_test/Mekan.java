package com.example.travelguidewebapplication.travel_plan_test;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "mekan")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Mekan extends CoreEntity {
    String ad;
    String tur;
    String atmosfer;
    String konum;
}
