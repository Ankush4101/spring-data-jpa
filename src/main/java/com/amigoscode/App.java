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

            jamila = studentRepository.save(jamila);

            System.out.println("Created By " + jamila.getCreatedBy());
            System.out.println("Created At " + jamila.getCreatedAt());
            System.out.println("Modified At " + jamila.getModifiedAt());
            System.out.println("Modified By " + jamila.getModifiedBy());

            System.out.println(studentRepository.count());

            // studentRepository.deleteById(1L);

            System.out.println(studentRepository.count());

            studentRepository.findById(1L);

            Book book = new Book();
            book.setTitle("Spring Data JPA");
            book.setStudent(jamila);

            bookRepository.save(book);

            bookRepository.getAllBooksDto().forEach(System.out::println);
        };
    }



}
