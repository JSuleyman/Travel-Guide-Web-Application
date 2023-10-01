package com.example.travelguidewebapplication.travel_plan;

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
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurants extends CoreEntity {
    String name;
    String type;
}
