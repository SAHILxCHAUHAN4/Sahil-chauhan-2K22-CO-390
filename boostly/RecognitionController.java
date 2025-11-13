package com.boostly.boostly.controller;

import com.boostly.boostly.dto.EndorsementRequestDTO;
import com.boostly.boostly.dto.RecognitionRequestDTO;
import com.boostly.boostly.model.Endorsement;
import com.boostly.boostly.model.Recognition;
import com.boostly.boostly.service.RecognitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecognitionController {

    private final RecognitionService recognitionService;

    public RecognitionController(RecognitionService recognitionService) {
        this.recognitionService = recognitionService;
    }

    @PostMapping("/recognitions")
    public ResponseEntity<Recognition> createRecognition(@RequestBody RecognitionRequestDTO request) {
        // Add @Valid annotation if using validation dependency
        Recognition newRecognition = recognitionService.giveRecognition(request);
        return new ResponseEntity<>(newRecognition, HttpStatus.CREATED);
    }

    @PostMapping("/endorsements")
    public ResponseEntity<Endorsement> createEndorsement(@RequestBody EndorsementRequestDTO request) {
        // Add @Valid annotation if using validation dependency
        Endorsement newEndorsement = recognitionService.endorseRecognition(request);
        return new ResponseEntity<>(newEndorsement, HttpStatus.CREATED);
    }
}