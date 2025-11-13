package com.boostly.boostly.repository;

import com.boostly.boostly.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByEmail(String email);
    
    // For Leaderboard: Find all, ordered by total credits (DESC) then ID (ASC)
    Page<Student> findAllByOrderByTotalCreditsReceivedDescIdAsc(Pageable pageable);
}
