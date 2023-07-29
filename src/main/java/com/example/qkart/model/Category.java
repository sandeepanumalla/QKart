package com.example.qkart.model;

import jakarta.persistence.*;

@Entity
public enum Category {
    MEN_CLOTHES,

    FEMALE_SHOES,

    E_CIGRARETTES;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
