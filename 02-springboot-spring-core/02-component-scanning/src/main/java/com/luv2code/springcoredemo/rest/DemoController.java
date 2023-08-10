package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //create a constructor for injections
    //1.define a private field for the dependency
    private Coach myCoach;
    //2.define a constructor for dependency injections
    @Autowired
    //@Autowired annotation tells Spring to inject a dependency,if you only have 1 constructor,@Autowired annotation is optional
    //Coach theCoach = new CricketCoach()
    //DemoController demoController = new DemoController(theCoach)
    //the Coach is a dependency or a helper of DemoController
    public DemoController(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
