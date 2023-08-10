package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//1.create configuration class
@Configuration
public class SportConfig {
    //2.define a bean method to configure a bean,the bean id defaults to the method name,the bean id can be changed by new name in brackets
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }


}
