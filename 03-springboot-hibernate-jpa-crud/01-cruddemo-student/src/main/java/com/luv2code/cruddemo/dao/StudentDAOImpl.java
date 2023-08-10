package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository support component scanning and translates JDBC exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {
    //define field for entity manager
    private EntityManager entityManager;
    //inject entity manager using constructor injection,so we can use the method of EntityManager
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement save method
    //add @Transactional since we are performing an update on the database,
    //we actually saving and performing an update on the database
    @Override
    @Transactional
    public void save(Student theStudent) {
    entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        //create query: Student is the name of JPA entity, it's the class/field name,not the name of database table
        //TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName",Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);
        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query,:theData,JPQL named parameters are prefixed with a colon,think theData is a placeholder and will be filled later
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student where lastName=:theData",Student.class);
        //set query parameters
        theQuery.setParameter("theData",theLastName);
        //return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(int id) {
        //retrieve the student
        Student theStudent = entityManager.find(Student.class,id);
        //delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
