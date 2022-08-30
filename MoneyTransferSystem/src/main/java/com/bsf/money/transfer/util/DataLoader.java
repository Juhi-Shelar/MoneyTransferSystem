package com.bsf.money.transfer.util;

import com.bsf.money.transfer.entities.Account;
import com.bsf.money.transfer.enums.ACCOUNT_TYPE;
import com.bsf.money.transfer.enums.CURRENCY;
import com.bsf.money.transfer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class DataLoader implements ApplicationRunner {
    private final AccountRepository accountRepository;

    @Autowired
    public DataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Account account1 = Account.builder()
                .accountNumber("723409127833")
                .balance(BigDecimal.valueOf(1000))
                .name("User1")
                .accountType(ACCOUNT_TYPE.SAVINGS)
                .branch("Pune")
                .currency(CURRENCY.INR)
                .created(new Timestamp(System.currentTimeMillis()).toString())
                .build();

        accountRepository.save(account1);

        Account account2 = Account.builder()
                .accountNumber("991234437041")
                .balance(BigDecimal.valueOf(1000))
                .name("User2")
                .accountType(ACCOUNT_TYPE.SAVINGS)
                .branch("Pune")
                .currency(CURRENCY.INR)
                .created(new Timestamp(System.currentTimeMillis()).toString())
                .build();

        accountRepository.save(account2);

        Account account3 = Account.builder()
                .accountNumber("723409127844")
                .balance(BigDecimal.valueOf(1000))
                .name("User3")
                .accountType(ACCOUNT_TYPE.SAVINGS)
                .branch("Pune")
                .currency(CURRENCY.INR)
                .created(new Timestamp(System.currentTimeMillis()).toString())
                .build();

        accountRepository.save(account3);

        Account account4 = Account.builder()
                .accountNumber("991234217041")
                .balance(BigDecimal.valueOf(1000))
                .name("User4")
                .accountType(ACCOUNT_TYPE.SAVINGS)
                .branch("Pune")
                .currency(CURRENCY.INR)
                .created(new Timestamp(System.currentTimeMillis()).toString())
                .build();

        accountRepository.save(account4);
    }
}
