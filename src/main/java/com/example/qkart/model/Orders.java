package com.example.qkart.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Temporal(TemporalType.DATE)
    private Date createdDate;
}
