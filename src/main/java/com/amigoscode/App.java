package com.amigoscode;

import com.amigoscode.book.Book;
import com.amigoscode.book.BookRepository;
import com.amigoscode.student.Student;
import com.amigoscode.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            BookRepository bookRepository) {
        return args -> {
            Student jamila = new Student(
                    "jamila", "bar", 18, "jamila@amigoscode.com"
            );

            studentRepository.save(jamila);


            studentRepository.findById(1L);

            Book book = new Book();
            book.setTitle("Spring Data JPA");
            book.setStudent(jamila);

            bookRepository.save(book);

            // bookRepository.findAll().forEach(System.out::println);

            studentRepository
                    .findStudentByIdWithBooks(1L)
                    .ifPresent(System.out::println);

        };
    }


}
