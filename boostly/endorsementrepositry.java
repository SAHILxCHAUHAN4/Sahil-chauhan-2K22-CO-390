package com.boostly.boostly.repository;

import com.boostly.boostly.model.Endorsement;
import com.boostly.boostly.model.Recognition;
import com.boostly.boostly.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Long> {

    boolean existsByEndorserAndRecognition(Student endorser, Recognition recognition);
    
    // For Leaderboard: Count all endorsements for recognitions received by a specific student
    @Query("SELECT COUNT(e) FROM Endorsement e WHERE e.recognition.receiver = :student")
    long countByStudent(@Param("student") Student student);
}
