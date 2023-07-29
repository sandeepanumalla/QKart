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

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @OneToOne(mappedBy = "id.user",cascade = CascadeType.ALL)
    private Kart kart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orderList;
}
