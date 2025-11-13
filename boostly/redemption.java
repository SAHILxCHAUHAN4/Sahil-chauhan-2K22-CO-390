package com.boostly.boostly.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Entity
@Table(name = "redemptions")
@Data
@NoArgsConstructor
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private int creditsRedeemed;

    @Column(nullable = false)
    private int voucherAmount; // In smallest currency unit (e.g., ₹)

    @Column(nullable = false, unique = true)
    private String voucherCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Instant timestamp;

    public Redemption(Student student, int creditsRedeemed, int voucherAmount, String voucherCode) {
        this.student = student;
        this.creditsRedeemed = creditsRedeemed;
        this.voucherAmount = voucherAmount;
        this.voucherCode = voucherCode;
        this.timestamp = Instant.now();
    }
}