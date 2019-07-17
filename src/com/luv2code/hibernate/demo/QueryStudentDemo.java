package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            // display the students
            displayTheStudents(theStudents);

            // query students: lastName = 'Doe'
            theStudents = session.createQuery("from Student s where s.lastName= 'Doe'").getResultList();

            // display the students
            System.out.println("\nStudents who have last name of Doe");
            displayTheStudents(theStudents);

            // query students: lastName = 'Doe' OR firstName = 'Daffy'
            theStudents = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();

            // display the students
            System.out.println("\nStudents who have last name of Doe or first name of Daffy");
            displayTheStudents(theStudents);

            // query students where email LIKE '%luv2code.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();

            // display the students
            System.out.println("\nStudents whose email ends with love2code.com");
            displayTheStudents(theStudents);

            // commit transaction
            session.getTransaction().commit();


            System.out.println("\nDone!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayTheStudents(List<Student> theStudents) {
        for (Student tempStudent: theStudents) {
            System.out.println(tempStudent);
        }
    }
}
