package com.cart_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name= "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;
}
