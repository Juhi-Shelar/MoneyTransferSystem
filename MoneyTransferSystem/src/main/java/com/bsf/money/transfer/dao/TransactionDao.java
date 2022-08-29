package com.bsf.money.transfer.dao;

import com.bsf.money.transfer.enums.TRANSACTION_METHOD;
import com.bsf.money.transfer.enums.TRANSACTION_TYPE;
import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.model.Transaction;
import com.bsf.money.transfer.repository.AccountRepository;
import com.bsf.money.transfer.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TransactionDao {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void debitAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        Account account = accountRepository.getAccountByAccountNumber(debitAccountNumber);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                UUID.randomUUID().toString(),
                debitAccountNumber,
                creditAccountNumber,
                amount,
                TRANSACTION_TYPE.AMOUNT_TRANSFER,
                TRANSACTION_METHOD.DEBIT
        ));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void creditAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        Account account = accountRepository.getAccountByAccountNumber(creditAccountNumber);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        transactionRepository.save(new Transaction(
                UUID.randomUUID().toString(),
                debitAccountNumber,
                creditAccountNumber,
                amount,
                TRANSACTION_TYPE.AMOUNT_TRANSFER,
                TRANSACTION_METHOD.CREDIT
        ));
    }
}
