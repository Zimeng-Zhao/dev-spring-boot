package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	//inject StudentDAO
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
				//creatStudent(studentDAO);
				creatMultipleStudent(studentDAO);
				//readStudent(studentDAO);
				//queryForStudents(studentDAO);
				//queryForStudentsByLastName(studentDAO);
				//updateStudent(studentDAO);
				//deleteStudent(studentDAO);
				//deleteAllStudents(studentDAO);
				};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("deleted row count :" + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//retrieve student based on id: primary key
		int studentId = 1;
		System.out.println("getting student id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);
		//change first name to "Scooby"
		System.out.println("updating student... ");
		myStudent.setFirstName("John");
		//update the student
		studentDAO.update(myStudent);
		//display the updated student
		System.out.println("updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Doe");
		//display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents = studentDAO.findAll();
		//display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readStudent(StudentDAO studentDAO) {
		//create a student object
		System.out.println("creating new student object ...");
		Student tempStudent = new Student("Daffy","Duck","Daffy.luv2code.com");

		//save the student
		System.out.println("saving the student ...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("saved student.Generate id: " + theId);

		//retrieve student based on id:primary key
		System.out.println("retrieving the student with id : " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("find the student: " + myStudent);
	}

	private void creatMultipleStudent(StudentDAO studentDAO) {
		//create multiple students
		System.out.println("creating new student object ...");
		Student tempStudent1 = new Student("John","Doe","Paul.luv2code.com");
		Student tempStudent2 = new Student("Mary","Doe","Mary.luv2code.com");
		Student tempStudent3 = new Student("Bonida","Doe","Bonida.luv2code.com");
		//save student objects
		System.out.println("saving student objects ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void creatStudent(StudentDAO studentDAO) {
		//create the student object
		System.out.println("creating new student object ...");
		Student tempStudent = new Student("Paul","Doe","Paul.luv2code.com");

		//save the student object
		System.out.println("saving student object ...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		System.out.println("saved student.Generate id:" + tempStudent.getId());

	}
}
