package com.bsf.money.transfer.service;

import com.bsf.money.transfer.dao.AccountDao;
import com.bsf.money.transfer.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountDao accountDao;

    public Account getAccountByAccountNumber(String accountNumber) {
        return accountDao.getAccountByAccountNumber(accountNumber);
    }
}
