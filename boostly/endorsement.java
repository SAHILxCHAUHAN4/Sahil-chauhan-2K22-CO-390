package com.boostly.boostly.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endorsements", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"endorser_id", "recognition_id"})
})
@Data
@NoArgsConstructor
public class Endorsement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endorser_id", nullable = false)
    private Student endorser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recognition_id", nullable = false)
    private Recognition recognition;

    public Endorsement(Student endorser, Recognition recognition) {
        this.endorser = endorser;
        this.recognition = recognition;
    }
}