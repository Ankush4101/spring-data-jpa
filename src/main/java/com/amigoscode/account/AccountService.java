package com.amigoscode.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transfer(Account from, Account to, BigDecimal amount) {
        from.setBalance(from.getBalance().subtract(amount));
        accountRepository.save(from);

        if(true) {
            // this rolls back transaction
            // throw new IllegalStateException("cannot set balance to account " + to.getId());
        }

        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(to);
    }
}
