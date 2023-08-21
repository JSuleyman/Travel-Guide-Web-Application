package com.example.travelguidewebapplication.util;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public class CoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    // TODO bunu en azinnan id ni o biri modellerden CoreEntity exdents elesek daha clean code olar
}
