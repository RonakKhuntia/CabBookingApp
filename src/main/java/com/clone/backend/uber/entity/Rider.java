package com.clone.backend.uber.entity;

import jakarta.persistence.*;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
