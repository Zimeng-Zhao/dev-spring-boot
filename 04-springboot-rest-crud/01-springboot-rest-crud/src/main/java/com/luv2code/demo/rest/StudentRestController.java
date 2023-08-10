package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //define a field,load this field with data,do it only once
    private List<Student> theStudents;
    //define @PostConstruct to load the student data,@PostConstruct is called only once when it's given bean is constructed
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();//initialize it as an empty list
        theStudents.add(new Student("Poornima","Patel"));
        theStudents.add(new Student("Mario","Rossi"));
        theStudents.add(new Student("Mary","Smith"));
    }

    //define an endpoint for "/students",return a list of all the students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }
    //define endpoint for "/students/{studentID},retriving a student by the index
    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable int studentID){
        //just index into the list...keep it simple for now
        //check the studentID against list size
        if((studentID >= theStudents.size()) || studentID < 0){
            throw new StudentNotFoundException("Student ID not found: " + studentID);
        }
        return theStudents.get(studentID);
    }


}
