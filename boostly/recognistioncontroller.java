package com.boostly.boostly.controller;

import com.boostly.boostly.dto.RedemptionRequestDTO;
import com.boostly.boostly.dto.RedemptionResponseDTO;
import com.boostly.boostly.service.RedemptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redemptions")
public class RedemptionController {

    private final RedemptionService redemptionService;

    public RedemptionController(RedemptionService redemptionService) {
        this.redemptionService = redemptionService;
    }

    @PostMapping
    public ResponseEntity<RedemptionResponseDTO> redeemCredits(@RequestBody RedemptionRequestDTO request) {
        // Add @Valid annotation if using validation dependency
        RedemptionResponseDTO response = redemptionService.redeemCredits(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}