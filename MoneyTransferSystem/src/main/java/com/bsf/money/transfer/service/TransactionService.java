package com.bsf.money.transfer.service;

import com.bsf.money.transfer.dao.TransactionDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionService {
    private final TransactionDao transactionDao;

    @Transactional(readOnly = true)
    public void amountTransfer(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        debitAmount(debitAccountNumber, creditAccountNumber, amount);
        creditAmount(debitAccountNumber, creditAccountNumber, amount);
    }

    public void debitAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        try {
            transactionDao.debitAmount(debitAccountNumber, creditAccountNumber, amount);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Somebody has already updated the amount for item:{} in concurrent transaction. Will try again...", debitAccountNumber);
            transactionDao.debitAmount(debitAccountNumber, creditAccountNumber, amount);
        }
    }

    public void creditAmount(String debitAccountNumber, String creditAccountNumber, BigDecimal amount) {
        try {
            transactionDao.creditAmount(debitAccountNumber, creditAccountNumber, amount);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.warn("Somebody has already updated the amount for item:{} in concurrent transaction. Will try again...", creditAccountNumber);
            transactionDao.creditAmount(debitAccountNumber, creditAccountNumber, amount);
        }
    }
}
