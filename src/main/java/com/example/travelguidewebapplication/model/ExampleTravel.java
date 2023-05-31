package com.example.travelguidewebapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExampleTravel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "yer_adi")
    private String yerAdi;

    @Column(name = "sehir")
    private String sehir;

    @Column(name = "detay_url")
    private String detayUrl;
}
