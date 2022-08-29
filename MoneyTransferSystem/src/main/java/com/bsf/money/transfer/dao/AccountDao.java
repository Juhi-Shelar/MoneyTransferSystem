package com.bsf.money.transfer.dao;

import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AccountDao {
    @Autowired
    AccountRepository accountRepository;

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}
