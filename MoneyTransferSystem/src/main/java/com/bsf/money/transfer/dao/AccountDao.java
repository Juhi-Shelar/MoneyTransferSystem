package com.bsf.money.transfer.dao;

import com.bsf.money.transfer.entities.Account;
import com.bsf.money.transfer.entities.Transaction;
import com.bsf.money.transfer.enums.TRANSACTION_METHOD;
import com.bsf.money.transfer.enums.TRANSACTION_TYPE;
import com.bsf.money.transfer.mapper.AccountMapper;
import com.bsf.money.transfer.repository.AccountRepository;
import com.bsf.money.transfer.repository.TransactionRepository;
import com.bsf.money.transfer.validator.Validator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Repository
public class AccountDao {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final Logger logger = LoggerFactory.getLogger(AccountDao.class);

    public com.bsf.money.transfer.model.Account getAccountByAccountNumber(String accountNumber) {
        com.bsf.money.transfer.model.Account account =
                accountMapper.toModel(accountRepository.getAccountByAccountNumber(accountNumber));

        if(account == null)
            throw new IllegalArgumentException("Account does not exist.");

        logger.info("Successfully retrieved account details for accountNumber = {}", accountNumber);
        return account;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void debitAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        // validation check on amount
        Objects.requireNonNull(amount, "Amount cannot be null");
        Validator.validateAmountNotNegative(amount);

        Account account = accountRepository.getAccountByAccountNumber(debitAccountNumber);

        // validation check on account
        Validator.validateBalanceIsSufficient(account, amount);

        account.setBalance(account.getBalance().subtract(amount));
        account.setModified(new Timestamp(System.currentTimeMillis()).toString());
        accountRepository.save(account);
        logger.info("Successfully debited {} amount from account = {}", amount, debitAccountNumber);

        transactionRepository.save(buildTransaction(debitAccountNumber, creditAccountNumber,
                amount, TRANSACTION_TYPE.AMOUNT_TRANSFER, TRANSACTION_METHOD.DEBIT));
        logger.info("Transaction logged successfully for account = {}", debitAccountNumber);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void creditAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        Account account = accountRepository.getAccountByAccountNumber(creditAccountNumber);

        account.setBalance(account.getBalance().add(amount));
        account.setModified(new Timestamp(System.currentTimeMillis()).toString());
        accountRepository.save(account);
        logger.info("Successfully credited {} amount to account = {}", amount, creditAccountNumber);

        transactionRepository.save(buildTransaction(debitAccountNumber, creditAccountNumber,
                amount, TRANSACTION_TYPE.AMOUNT_TRANSFER, TRANSACTION_METHOD.CREDIT));
        logger.info("Transaction logged successfully for account = {}", creditAccountNumber);
    }


    public void logTransaction(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {

        transactionRepository.save(buildTransaction(debitAccountNumber, creditAccountNumber,
                amount, TRANSACTION_TYPE.AMOUNT_TRANSFER, TRANSACTION_METHOD.DEBIT));

        transactionRepository.save(buildTransaction(debitAccountNumber, creditAccountNumber,
                amount, TRANSACTION_TYPE.AMOUNT_TRANSFER, TRANSACTION_METHOD.CREDIT));
    }

    private Transaction buildTransaction(String debitAccountNumber, String creditAccountNumber,
                                         BigDecimal amount, TRANSACTION_TYPE transactionType, TRANSACTION_METHOD transactionMethod) {
        return Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .debitAccountNumber(debitAccountNumber)
                .creditAccountNumber(creditAccountNumber)
                .amount(amount)
                .transactionType(transactionType)
                .transactionMethod(transactionMethod)
                .transactionTimeStamp(new Timestamp(System.currentTimeMillis()).toString())
                .build();
    }

}
