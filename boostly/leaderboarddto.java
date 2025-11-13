package com.boostly.boostly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaderboardDTO {
    private Long studentId;
    private String name;
    private long totalCreditsReceived;
    private long recognitionsReceivedCount;
    private long endorsementsReceivedCount;
}