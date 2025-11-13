package com.boostly.boostly.controller;

import com.boostly.boostly.dto.LeaderboardDTO;
import com.boostly.boostly.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public ResponseEntity<List<LeaderboardDTO>> getLeaderboard(
            @RequestParam(defaultValue = "10") int limit) {
        
        if (limit <= 0) {
            limit = 10;
        }
        
        List<LeaderboardDTO> leaderboard = leaderboardService.getLeaderboard(limit);
        return ResponseEntity.ok(leaderboard);
    }
}