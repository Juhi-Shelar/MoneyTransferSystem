package com.bsf.money.transfer.service;

import com.bsf.money.transfer.dao.AccountDao;
import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.repository.TransactionRepository;
import com.bsf.money.transfer.validator.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class AccountService {
    private final AccountDao accountDao;
    private final TransactionRepository transactionRepository;
    private final Logger logger = LoggerFactory.getLogger(AccountDao.class);

    public Account getAccountByAccountNumber(String accountNumber) {
        logger.info("Request parameters: accountNumber = {}", accountNumber);
        // validate account number
        Validator.validateAccountNumber(accountNumber);

        return accountDao.getAccountByAccountNumber(accountNumber);
    }

    @Transactional(readOnly = true)
    public void amountTransfer(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        logger.info("Request parameters: debitAccountNumber = {}, creditAccountNumber = {}, amount = {} ",
                debitAccountNumber, creditAccountNumber, amount);

        // validate bith the accounts
        validateAccounts(debitAccountNumber, creditAccountNumber);

        // debit from source account
        debitAmount(debitAccountNumber, creditAccountNumber, amount);

        // credit into destination account
        creditAmount(debitAccountNumber, creditAccountNumber, amount);
    }

    public void debitAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        try {
            accountDao.debitAmount(debitAccountNumber, creditAccountNumber, amount);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.warn("Transaction in progress for account :{}. Will try again...", debitAccountNumber);
            accountDao.debitAmount(debitAccountNumber, creditAccountNumber, amount);
        }
    }

    public void creditAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        try {
            accountDao.creditAmount(debitAccountNumber, creditAccountNumber, amount);
        } catch (ObjectOptimisticLockingFailureException e) {
            logger.warn("STransaction in progress for account:{}. Will try again...", creditAccountNumber);
            accountDao.creditAmount(debitAccountNumber, creditAccountNumber, amount);
        }
    }

    public void validateAccounts(String debitAccountNumber, String creditAccountNumber) {
        // validate account number
        Validator.validateAccountNumber(debitAccountNumber);
        Validator.validateAccountNumber(creditAccountNumber);
        Validator.validateAccountIsDifferent(debitAccountNumber, creditAccountNumber);

        // check if account exists
        if(accountDao.getAccountByAccountNumber(debitAccountNumber) == null ||
        accountDao.getAccountByAccountNumber(creditAccountNumber) ==null )
            throw new IllegalArgumentException("Account does not exist");

    }

}
