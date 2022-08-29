package com.bsf.money.transfer.util;

import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DataLoader implements ApplicationRunner {
    private AccountRepository accountRepository;

    @Autowired
    public DataLoader(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        accountRepository.save(new Account(UUID.randomUUID().toString(), "121", new BigDecimal(1000), 1L));

        accountRepository.save(new Account(UUID.randomUUID().toString(), "122", new BigDecimal(1000), 1L));
    }
}
