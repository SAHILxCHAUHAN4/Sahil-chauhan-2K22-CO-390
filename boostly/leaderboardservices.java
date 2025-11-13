package com.boostly.boostly.service;

import com.boostly.boostly.dto.LeaderboardDTO;
import com.boostly.boostly.model.Student;
import com.boostly.boostly.repository.EndorsementRepository;
import com.boostly.boostly.repository.RecognitionRepository;
import com.boostly.boostly.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final StudentRepository studentRepository;
    private final RecognitionRepository recognitionRepository;
    private final EndorsementRepository endorsementRepository;

    public LeaderboardService(StudentRepository studentRepository, RecognitionRepository recognitionRepository, EndorsementRepository endorsementRepository) {
        this.studentRepository = studentRepository;
        this.recognitionRepository = recognitionRepository;
        this.endorsementRepository = endorsementRepository;
    }

    @Transactional(readOnly = true)
    public List<LeaderboardDTO> getLeaderboard(int limit) {
        // Pageable will handle the sorting and limiting
        // Sort by totalCreditsReceived (DESC), then id (ASC)
        Pageable pageable = PageRequest.of(0, limit);
        
        Page<Student> topStudents = studentRepository.findAllByOrderByTotalCreditsReceivedDescIdAsc(pageable);

        return topStudents.stream()
                .map(this::mapToLeaderboardDTO)
                .collect(Collectors.toList());
    }

    private LeaderboardDTO mapToLeaderboardDTO(Student student) {
        // Query for counts
        long recognitionsCount = recognitionRepository.countByReceiver(student);
        long endorsementsCount = endorsementRepository.countByStudent(student);

        return new LeaderboardDTO(
                student.getId(),
                student.getName(),
                student.getTotalCreditsReceived(),
                recognitionsCount,
                endorsementsCount
        );
    }
}