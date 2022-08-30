package com.bsf.money.transfer.model;

import com.bsf.money.transfer.enums.TRANSACTION_METHOD;
import com.bsf.money.transfer.enums.TRANSACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String transactionId;
    private String debitAccountNumber;
    private String creditAccountNumber;
    private BigDecimal amount;
    private String currency;
    private TRANSACTION_TYPE transactionType;
    private TRANSACTION_METHOD transactionMethod;
}
