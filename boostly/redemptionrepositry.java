package com.boostly.boostly.repository;

import com.boostly.boostly.model.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
    // No custom methods needed for now
}