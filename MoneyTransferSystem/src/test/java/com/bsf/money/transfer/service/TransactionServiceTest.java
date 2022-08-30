package com.bsf.money.transfer.service;

import com.bsf.money.transfer.MoneyTransferSystemApplication;
import com.bsf.money.transfer.entities.Account;
import com.bsf.money.transfer.repository.AccountRepository;
import com.bsf.money.transfer.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MoneyTransferSystemApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionServiceTest {
    @Autowired AccountRepository accountRepository;
    @Autowired AccountService accountService;

    private final List<Integer> amounts = Arrays.asList(300, 200);

    @Test
    void shouldTransferAmount_withoutConcurrency() {
        // given
        final String sourceAccount = "121";
        final String destAccount = "122";

        Account source = accountRepository.getAccountByAccountNumber(sourceAccount);
        Account dest = accountRepository.getAccountByAccountNumber(destAccount);

        assertEquals(0, source.getBalance().compareTo(new BigDecimal(1000)));
        assertEquals(0, source.getBalance().compareTo(new BigDecimal(1000)));

        assertEquals(0L, source.getVersion());
        assertEquals(0L, dest.getVersion());

        // when
        for(final int amount: amounts) {
            accountService.amountTransfer(sourceAccount, destAccount, BigDecimal.valueOf(amount));
        }

        // then
        source = accountRepository.getAccountByAccountNumber(sourceAccount);
        dest = accountRepository.getAccountByAccountNumber(destAccount);

        assertEquals(0, source.getBalance().compareTo(new BigDecimal(500)));
        assertEquals(0, dest.getBalance().compareTo(new BigDecimal(1500)));

        assertEquals(2L, source.getVersion());
        assertEquals(2L, dest.getVersion());
    }

    @Test
    void shouldTransferAmount_withoutConcurrency_usingOptimisticLockingHandling() throws InterruptedException {
        // given
        final String sourceAccount = "121";
        final String destAccount = "122";

        Account source = accountRepository.getAccountByAccountNumber(sourceAccount);
        Account dest = accountRepository.getAccountByAccountNumber(destAccount);

        assertEquals(0, source.getBalance().compareTo(new BigDecimal(1000)));
        assertEquals(0, source.getBalance().compareTo(new BigDecimal(1000)));

        assertEquals(0L, source.getVersion());
        assertEquals(0L, dest.getVersion());

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(amounts.size());

        for (final int amount : amounts) {
            executor.execute(() -> accountService.amountTransfer(sourceAccount, destAccount, BigDecimal.valueOf(amount)));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // then
        source = accountRepository.getAccountByAccountNumber(sourceAccount);
        dest = accountRepository.getAccountByAccountNumber(destAccount);

        assertEquals(0, source.getBalance().compareTo(new BigDecimal(500)));
        assertEquals(0, dest.getBalance().compareTo(new BigDecimal(1500)));

        assertEquals(2L, source.getVersion());
        assertEquals(2L, dest.getVersion());
    }
}
