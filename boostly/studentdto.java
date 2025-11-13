package com.boostly.boostly.dto;

import lombok.Data;
// Assuming validation dependency is added
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotEmpty;

@Data
public class StudentDTO {
    // @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    // @NotEmpty(message = "Email cannot be empty")
    // @Email(message = "Email should be valid")
    private String email;
}