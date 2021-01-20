package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;


public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();
			
			Student tempMaryStudent = session.get(Student.class, 2);
			System.out.println("Loaded student: " + tempMaryStudent.getFirstName() + " " + tempMaryStudent.getLastName());
			System.out.println("Courses: " + tempMaryStudent.getCourses());
			
			Course tempCourse1 = new Course("Rubick's Cube - How to Speed Cube");
			Course tempCourse2 = new Course("Atari 2600 - Game Development");
			
			tempCourse1.addStudent(tempMaryStudent);
			tempCourse2.addStudent(tempMaryStudent);
			System.out.println("Saving the courses...");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
