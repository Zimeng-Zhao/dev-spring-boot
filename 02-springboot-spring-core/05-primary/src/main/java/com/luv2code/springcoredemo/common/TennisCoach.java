package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
//marks the class as a spring bean,makes it available for dependency injection
public class TennisCoach implements Coach{

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
