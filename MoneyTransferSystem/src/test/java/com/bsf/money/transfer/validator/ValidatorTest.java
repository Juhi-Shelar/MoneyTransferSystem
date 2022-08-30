package com.bsf.money.transfer.validator;

import com.bsf.money.transfer.entities.Account;
import com.bsf.money.transfer.exception.InvalidBalanceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidatorTest {

    @Test
    public void testValidateAccountNumber_whenValidAccountNumber_doNothing() {
        // given
        String validAccountNumber = "991234437041";
        // then
        Validator.validateAccountNumber(validAccountNumber);
    }

    @Test
    public void testValidateAccountNumber_whenInvalidAccountNumber_throwsException() {
        // given
        String invalidAccountNumber = "1234";
        // then
        assertThrows(IllegalArgumentException.class, ()-> Validator.validateAccountNumber(invalidAccountNumber));
    }

    @Test
    public void testValidateAmountNotNegative_whenValidAmount_doNothing() {
        // given
        BigDecimal validAmount = new BigDecimal(1000);
        // then
        Validator.validateAmountNotNegative(validAmount);
    }

    @Test
    public void testValidateAccountNumber_whenInvalidAmount_throwsException() {
        // given
        BigDecimal invalidAmount = new BigDecimal(-1);
        // then
        assertThrows(IllegalArgumentException.class, ()-> Validator.validateAmountNotNegative(invalidAmount));
    }

    @Test
    public void testValidateAccountIsDifferent_whenValidAccounts_doNothing() {
        //given
        String debitAccount = "12345";
        String creditAccount = "65321";

        // then
        Validator.validateAccountIsDifferent(debitAccount,creditAccount);
    }

    @Test
    public void testValidateAccountIsDifferent_whenInvalidAccounts_throwsException() {
        // given
        String debitAccount = "12345";
        String creditAccount = "12345";

        // then
        assertThrows(IllegalArgumentException.class, ()->
                Validator.validateAccountIsDifferent(debitAccount,creditAccount));
    }

    @Test
    public void testValidateBalanceIsSufficient_whenValidBalance_doNothing() {
        // given
        Account account = Account.builder()
                .balance(BigDecimal.valueOf(1000))
                .build();

        // then
        Validator.validateBalanceIsSufficient(account,BigDecimal.valueOf(500));
    }

    @Test
    public void testValidateBalanceIsSufficient_whenInValidBalance_doNothing() {
        // given
        Account account = Account.builder()
                .balance(BigDecimal.valueOf(400))
                .build();

        // then
        assertThrows(InvalidBalanceException.class, () ->
                Validator.validateBalanceIsSufficient(account, BigDecimal.valueOf(500)));
    }

}
