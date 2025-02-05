package com.amigoscode;

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
            Student student = new Student(
                    "Foo",
                    "Bar",
                    18,
                    "foobar@amigoscode.com"
            );
            studentRepository.save(student);
            System.out.println(studentRepository.count());
            System.out.println(studentRepository.findAll());
            System.out.println(studentRepository.findById(1L));
            System.out.println(studentRepository.existsById(1L));
            System.out.println(studentRepository.existsById(2L));
            studentRepository.deleteById(1L);
            System.out.println(studentRepository.count());
        };
    }

}
