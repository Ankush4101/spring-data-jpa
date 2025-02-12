package com.amigoscode;

import com.amigoscode.account.Account;
import com.amigoscode.account.AccountRepository;
import com.amigoscode.account.AccountService;
import com.amigoscode.book.Book;
import com.amigoscode.book.BookRepository;
import com.amigoscode.student.Student;
import com.amigoscode.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;

@SpringBootApplication
@EnableJpaAuditing
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            AccountRepository accountRepository,
            AccountService accountService) {
        return args -> {
            Account a = new Account();
            a.setBalance(new BigDecimal("100"));
            accountRepository.save(a);

            Account b = new Account();
            b.setBalance(new BigDecimal("100"));
            accountRepository.save(b);

            accountService.transfer(a, b, BigDecimal.TEN);

            accountRepository.findAll().forEach(acc -> {
                System.out.println(acc.getId() + " " + acc.getBalance());
            });

        };
    }


}
