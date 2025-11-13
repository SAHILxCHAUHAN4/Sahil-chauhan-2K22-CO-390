package com.boostly.boostly.service;

import com.boostly.boostly.dto.RedemptionRequestDTO;
import com.boostly.boostly.dto.RedemptionResponseDTO;
import com.boostly.boostly.exception.ResourceNotFoundException;
import com.boostly.boostly.exception.ValidationException;
import com.boostly.boostly.model.Redemption;
import com.boostly.boostly.model.Student;
import com.boostly.boostly.repository.RedemptionRepository;
import com.boostly.boostly.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RedemptionService {

    @Value("${app.redemption.rate}")
    private int redemptionRate; // e.g., 5 (for ₹5 per credit)

    private final StudentRepository studentRepository;
    private final RedemptionRepository redemptionRepository;

    public RedemptionService(StudentRepository studentRepository, RedemptionRepository redemptionRepository) {
        this.studentRepository = studentRepository;
        this.redemptionRepository = redemptionRepository;
    }

    @Transactional
    public RedemptionResponseDTO redeemCredits(RedemptionRequestDTO request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + request.getStudentId()));

        // Rule: Can only redeem credits they have
        if (student.getRedeemableBalance() < request.getCreditsToRedeem()) {
            throw new ValidationException("Insufficient redeemable balance. Available: " + student.getRedeemableBalance());
        }

        // Rule: Must redeem at least 1 credit
        if (request.getCreditsToRedeem() <= 0) {
            throw new ValidationException("Must redeem at least 1 credit.");
        }

        // Calculate voucher value
        int voucherAmount = request.getCreditsToRedeem() * redemptionRate;
        String voucherCode = "BOOSTLY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Deduct from balance
        student.setRedeemableBalance(student.getRedeemableBalance() - request.getCreditsToRedeem());
        studentRepository.save(student);

        // Log the redemption
        Redemption redemption = new Redemption(student, request.getCreditsToRedeem(), voucherAmount, voucherCode);
        redemptionRepository.save(redemption);

        return new RedemptionResponseDTO(
                "Redemption successful!",
                voucherCode,
                voucherAmount,
                student.getRedeemableBalance()
        );
    }
}
