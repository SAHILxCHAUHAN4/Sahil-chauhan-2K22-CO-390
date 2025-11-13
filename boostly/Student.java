package com.boostly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data // Lombok: auto-generates getters, setters, toString, etc.
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    // The balance of credits they can give away this month
    @Column(nullable = false)
    private int sendingBalance = 100;

    // The balance of credits they have received and can redeem
    @Column(nullable = false)
    private int redeemableBalance = 0;

    // Lifetime total credits received (for leaderboard)
    @Column(nullable = false)
    private long totalCreditsReceived = 0L;

    public Student(String email, String name) {
        this.email = email;
        this.name = name;
    }
}