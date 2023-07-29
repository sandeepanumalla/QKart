package com.example.qkart.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Transient
    private int quantity;

    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
