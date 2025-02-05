package com.amigoscode;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            generateRandomStudents(studentRepository);
            System.out.println(studentRepository.count());
            System.out.println(studentRepository.findAll());
        };
    }

    private static void paging(StudentRepository studentRepository) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Student> page = studentRepository.findAll(pageable);
        System.out.println(page);
        System.out.println(page.getTotalPages());
        System.out.println(page.getTotalElements());
        System.out.println(page.getSize());
        page.get().forEach(System.out::println);
        pageable = PageRequest.of(11, 1000);
        page = studentRepository.findAll(pageable);
        System.out.println("--------");
        page.get().forEach(System.out::println);
    }

    private static void sort(StudentRepository studentRepository) {
        Sort sort = Sort.by("firstName").descending()
                .and(Sort.by("age").descending());
        studentRepository.findAll(sort).forEach(s -> {
            System.out.println(s.getFirstName() + " " + s.getAge());
        });
    }

    private static void generateRandomStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = """
                    %s.%s@amigoscode.edu
                    """.formatted(firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    faker.number().numberBetween(17, 55),
                    email
            );
            studentRepository.save(student);
        }
    }

}
