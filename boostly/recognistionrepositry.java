package com.boostly.boostly.repository;

import com.boostly.boostly.model.Recognition;
import com.boostly.boostly.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecognitionRepository extends JpaRepository<Recognition, Long> {
    
    // For Leaderboard
    long countByReceiver(Student receiver);
}