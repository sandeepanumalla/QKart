package com.example.qkart.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "kart")
public class Kart {

    @EmbeddedId
    private KartProductKey id;


    private int quantity;

    @Embeddable
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KartProductKey implements Serializable {

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

    }
}
