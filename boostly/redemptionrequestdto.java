package com.boostly.boostly.dto;

import lombok.Data;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotNull;

@Data
public class RedemptionRequestDTO {
    // @NotNull
    private Long studentId;
    
    // @Min(value = 1, message = "Must redeem at least 1 credit")
    private int creditsToRedeem;
}
