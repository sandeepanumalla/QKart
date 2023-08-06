package com.example.qkart.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String username;

    private String firstName;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @OneToOne
    @JoinColumn(name = "kart_id")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Orders> orderList;
}
