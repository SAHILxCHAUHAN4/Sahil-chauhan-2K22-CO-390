package com.boostly.boostly.scheduler;

import com.boostly.boostly.service.StudentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CreditResetScheduler {

    private final StudentService studentService;

    public CreditResetScheduler(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Runs at 00:00 on the 1st day of every month.
     * (second, minute, hour, day-of-month, month, day-of-week)
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void scheduleMonthlyCreditReset() {
        System.out.println("Executing monthly credit reset task...");
        studentService.resetMonthlyAllowances();
        System.out.println("Monthly credit reset task finished.");
    }
}