package com.bsf.money.transfer.validator;

import com.bsf.money.transfer.entities.Account;
import com.bsf.money.transfer.exception.InvalidBalanceException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static void validateAccountNumber(String accountNumber) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(accountNumber);

        if(accountNumber.length() != 12 ||  m.find())
            throw new IllegalArgumentException("Please enter valid account number");
    }
    public static void validateAmountNotNegative(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }

    public static void validateAccountIsDifferent(String debitAccNumber, String creditAccNumber) {
        if (debitAccNumber.equals(creditAccNumber)) {
            throw new IllegalArgumentException("Accounts must be different");
        }
    }

    public static void validateBalanceIsSufficient(Account account, BigDecimal amount) {
        if(account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0 ) {
            throw new InvalidBalanceException(amount, account.getBalance());
        }
    }

}
