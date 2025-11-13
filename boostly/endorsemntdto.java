package com.boostly.boostly.dto;

import lombok.Data;
// import jakarta.validation.constraints.NotNull;

@Data
public class EndorsementRequestDTO {
    // @NotNull
    private Long endorserId;
    
    // @NotNull
    private Long recognitionId;
}