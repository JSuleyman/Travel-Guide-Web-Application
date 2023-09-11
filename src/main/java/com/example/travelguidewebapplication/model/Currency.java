//package com.example.travelguidewebapplication.model;
//
//import com.example.travelguidewebapplication.util.CoreEntity;
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.util.Collection;
//
//@Entity
//@Data
//@Table(name = "currency")
//@AllArgsConstructor
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Builder
//public class Currency extends CoreEntity {
//    @Column(unique = true, nullable = false)
//    String currency;
//    Double exchange;
//
//    @Getter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "currencyId")
//    Collection<Expenses> expensesCollection;
//}
