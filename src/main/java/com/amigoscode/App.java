package com.amigoscode;

import com.amigoscode.book.Book;
import com.amigoscode.book.BookRepository;
import com.amigoscode.student.Student;
import com.amigoscode.student.StudentRepository;
import com.amigoscode.student.StudentService;
import com.amigoscode.studentidcard.StudentIdCard;
import com.amigoscode.studentidcard.StudentIdCardRepository;
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
import java.util.Set;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository,
            BookRepository bookRepository,
            StudentService studentService) {
        return args -> {
            Student jamila = new Student(
                    "jamila", "bar", 18, "jamila@amigoscode.com"
            );

            Book book = new Book();
            book.setTitle("Spring AI");

            jamila.addBook(book);

            studentRepository.save(jamila);

            System.out.println(bookRepository.count());

            jamila.removeBook(book);

            studentRepository.save(jamila);

            System.out.println(bookRepository.count());

            studentRepository.selectStudentWithBooks().forEach(s -> {
                System.out.println(s.getFirstName());
                System.out.println("Books size: " + s.getBooks().size());
            });
            System.out.println(studentRepository.count());

        };
    }

    private static void oneToManyExamples(StudentRepository studentRepository, BookRepository bookRepository, StudentService studentService) {
        // Jamila
        Student jamila = new Student(
                "jamila", "bar", 18, "jamila@amigoscode.com"
        );

        Book jamilasBook = new Book();
        jamilasBook.setTitle("Spring AI");
        jamilasBook.setStudent(jamila);

        jamila.setBooks(Set.of(jamilasBook));

        // Save student and jpa will save the book too
        studentRepository.save(jamila);

        // List all books with their students

        System.out.println("all books");

        bookRepository.findAll().forEach(b -> {
            System.out.println(b.getTitle());
            System.out.println(b.getStudent().getFirstName());
            System.out.println();
        });

        System.out.println("getStudentWithBooks");

        studentService.getStudentWithBooks(1L).ifPresent(s -> {
            System.out.println(s.getFirstName());
            s.getBooks().forEach(b -> {
                System.out.println("Book: " + b.getTitle());
            });
        });

        System.out.println("all students");

        // list all students
//            studentRepository.selectStudentWithBooks().forEach(s -> {
//                System.out.println(s.getFirstName());
//                s.getBooks().forEach(b -> {
//                    System.out.println("Book: " + b.getTitle());
//                });
//            });
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
