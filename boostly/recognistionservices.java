package com.boostly.boostly.service;

import com.boostly.boostly.dto.EndorsementRequestDTO;
import com.boostly.boostly.dto.RecognitionRequestDTO;
import com.boostly.boostly.exception.ResourceNotFoundException;
import com.boostly.boostly.exception.ValidationException;
import com.boostly.boostly.model.Endorsement;
import com.boostly.boostly.model.Recognition;
import com.boostly.boostly.model.Student;
import com.boostly.boostly.repository.EndorsementRepository;
import com.boostly.boostly.repository.RecognitionRepository;
import com.boostly.boostly.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecognitionService {

    private final StudentRepository studentRepository;
    private final RecognitionRepository recognitionRepository;
    private final EndorsementRepository endorsementRepository;

    public RecognitionService(StudentRepository studentRepository, RecognitionRepository recognitionRepository, EndorsementRepository endorsementRepository) {
        this.studentRepository = studentRepository;
        this.recognitionRepository = recognitionRepository;
        this.endorsementRepository = endorsementRepository;
    }

    @Transactional
    public Recognition giveRecognition(RecognitionRequestDTO request) {
        // Rule: Cannot send to self
        if (request.getSenderId().equals(request.getReceiverId())) {
            throw new ValidationException("Self-recognition is not allowed.");
        }

        Student sender = studentRepository.findById(request.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found with id: " + request.getSenderId()));

        Student receiver = studentRepository.findById(request.getReceiverId())
                .orElseThrow(() -> new ResourceNotFoundException("Receiver not found with id: " + request.getReceiverId()));

        // Rule: Cannot send more than current sending balance
        if (sender.getSendingBalance() < request.getCredits()) {
            throw new ValidationException("Insufficient sending balance. Available: " + sender.getSendingBalance());
        }
        
        // Rule: Credit value must be positive
        if (request.getCredits() <= 0) {
            throw new ValidationException("Credits must be a positive number.");
        }

        // Update balances
        sender.setSendingBalance(sender.getSendingBalance() - request.getCredits());
        receiver.setRedeemableBalance(receiver.getRedeemableBalance() + request.getCredits());
        receiver.setTotalCreditsReceived(receiver.getTotalCreditsReceived() + request.getCredits());

        // Save updated students
        studentRepository.save(sender);
        studentRepository.save(receiver);

        // Create and save recognition
        Recognition recognition = new Recognition(sender, receiver, request.getCredits(), request.getMessage());
        return recognitionRepository.save(recognition);
    }

    @Transactional
    public Endorsement endorseRecognition(EndorsementRequestDTO request) {
        Student endorser = studentRepository.findById(request.getEndorserId())
                .orElseThrow(() -> new ResourceNotFoundException("Endorser not found with id: " + request.getEndorserId()));

        Recognition recognition = recognitionRepository.findById(request.getRecognitionId())
                .orElseThrow(() -> new ResourceNotFoundException("Recognition not found with id: " + request.getRecognitionId()));

        // Rule: Cannot endorse more than once
        if (endorsementRepository.existsByEndorserAndRecognition(endorser, recognition)) {
            throw new ValidationException("You have already endorsed this recognition.");
        }

        Endorsement endorsement = new Endorsement(endorser, recognition);
        return endorsementRepository.save(endorsement);
    }
}
