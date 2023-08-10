package com.luv2code.springcoredemo.common;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.processing.SupportedAnnotationTypes;

@Component
//marks the class as a spring bean,makes it available for dependency injection
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//change the scope:prototype means new object instance for every injection
public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In constructor：" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}

