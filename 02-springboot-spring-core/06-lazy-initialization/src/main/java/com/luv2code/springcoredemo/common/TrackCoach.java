package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Lazy only initialize beans when they are needed,if I don't add @Lazy, all beans will initialize by default
@Lazy
//marks the class as a spring bean,makes it available for dependency injection
public class TrackCoach implements Coach{
    public TrackCoach(){
        System.out.println("In constructor：" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }
}
