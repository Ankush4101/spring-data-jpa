package com.amigoscode;

import com.amigoscode.student.Student;
import com.amigoscode.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student jamila = new Student(
                    "jamila", "bar", 18, "jamila@amigoscode.com"
            );

            studentRepository.save(jamila);

            System.out.println(studentRepository.count());

            studentRepository.deleteById(1L);

            System.out.println(studentRepository.count());
        };
    }



}
