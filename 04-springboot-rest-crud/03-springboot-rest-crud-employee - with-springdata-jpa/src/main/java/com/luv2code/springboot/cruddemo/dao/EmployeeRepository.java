package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//entity type:Employee primary key type:integer
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    //that's it ... ne need to write any codes
}
