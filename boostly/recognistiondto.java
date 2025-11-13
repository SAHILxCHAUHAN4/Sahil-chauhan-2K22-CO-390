package com.boostly.boostly.dto;

import lombok.Data;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotNull;

@Data
public class RecognitionRequestDTO {
    // @NotNull
    private Long senderId;
    
    // @NotNull
    private Long receiverId;
    
    // @Min(value = 1, message = "Must send at least 1 credit")
    private int credits;
    
    private String message;
}
