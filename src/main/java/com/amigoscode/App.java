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

import java.util.Optional;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository) {
        return args -> {
            Student foo = new Student(
                    "foo", "bar", 18, "foo@amigoscode.com"
            );

            foo = studentRepository.save(foo);

            Book foosBook = new Book();
            foosBook.setTitle("Spring Data JPA");
            foosBook.setStudent(foo);
            bookRepository.save(foosBook);

            // Jamila

            Student jamila = new Student(
                    "jamila", "bar", 18, "jamila@amigoscode.com"
            );

            jamila = studentRepository.save(jamila);

            Book jamilasBook = new Book();
            jamilasBook.setTitle("Spring AI");
            jamilasBook.setStudent(jamila);
            bookRepository.save(jamilasBook);

            // List all books with their students

            System.out.println("all books");

            bookRepository.findAll().forEach(b -> {
                System.out.println(b.getTitle());
                System.out.println(b.getStudent().getFirstName());
                System.out.println();
            });

            bookRepository.deleteAll();

            System.out.println("Total books after delete " + bookRepository.count());
            System.out.println("Total students after delete " + studentRepository.count());
        };
    }

    private static void lifeCycle(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        Student student = new Student(
                "foo", "bar", 18, "fb@amigoscode.com"
        );

        student = studentRepository.save(student);

        StudentIdCard studentIdCard = new StudentIdCard();
        studentIdCard.setCardNumber("12345");
        studentIdCard.setStudent(student);

        studentIdCardRepository.save(studentIdCard);
    }

    private static void uniVsBiderectional(StudentRepository studentRepository) {
        StudentIdCard studentIdCard = new StudentIdCard();
        studentIdCard.setCardNumber("12345");

        Student student = new Student(
                "foo", "bar", 18, "fb@amigoscode.com"
        );

        student.setStudentIdCard(studentIdCard);
        studentIdCard.setStudent(student);
        studentRepository.save(student);
        Optional<Student> s = studentRepository.findById(1L);
        System.out.println(s.get().getStudentIdCard());
    }

    private static void example1(StudentRepository studentRepository, StudentIdCardRepository studentIdCardRepository) {
        lifeCycle(studentRepository, studentIdCardRepository);
        System.out.println("-----------");
        Optional<StudentIdCard> card = studentIdCardRepository.findById(1L);
        System.out.println(card.get().getId());
        System.out.println(card.get().getCardNumber());
        System.out.println(card.get().getCreatedAt());
        Optional<StudentIdCard> studentIdCardByIdWithStudent =
                studentIdCardRepository.findStudentIdCardByIdWithStudent(1L);
        System.out.println(studentIdCardByIdWithStudent.get().getStudent());
        System.out.println(card.get().getStudent());
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
        for (int i = 0; i < 2; i++) {
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
