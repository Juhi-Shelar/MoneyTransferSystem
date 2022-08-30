package com.bsf.money.transfer.dao;

import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountDaoTest {
    @Mock
    private AccountDao accountDao;
    @Mock
    private AccountRepository accountRepository;

    @Test
    public void testGetAccountByAccountNumber_forInvalidAccount() {
        assertThrows(IllegalArgumentException.class, ()-> accountDao.getAccountByAccountNumber("124"));
    }

    @Test
    public void testGetAccountByAccountNumber_forValidAccount() {
        // given
        Account mockAccount = Account.builder()
                .accountNumber("723409127833")
                .name("User1")
                .build();
        when(accountDao.getAccountByAccountNumber(any())).thenReturn(mockAccount);

        String validAccountNumber = "723409127833";
        // when
        Account account = accountDao.getAccountByAccountNumber(validAccountNumber);

        // then
        Assertions.assertNotNull(account);
        Assertions.assertEquals("User1", account.getName());
    }

    @Test
    public void testDebitAmount_forInvalidAmount() {
        assertThrows(IllegalArgumentException.class, ()-> accountDao.debitAmount(
                "124", "456", BigDecimal.valueOf(-10)));
    }
}
