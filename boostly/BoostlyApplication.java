package com.boostly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Enables the credit reset scheduler
public class BoostlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoostlyApplication.class, args);
    }

}