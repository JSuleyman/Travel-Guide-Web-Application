package com.example.travelguidewebapplication.model;

import com.example.travelguidewebapplication.util.CoreEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "sales_receipts")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SalesReceipt extends CoreEntity {
    private String name;

    private String type;

    @Lob
    @Column(name = "image_data", length = 100000)
    private byte[] imageData;

    @JoinColumn(name = "expense_id", referencedColumnName = "id", nullable = false)
    @OneToOne
    Expenses expense;
}
