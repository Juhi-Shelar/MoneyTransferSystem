package com.bsf.money.transfer.model;

import com.bsf.money.transfer.enums.TRANSACTION_METHOD;
import com.bsf.money.transfer.enums.TRANSACTION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @Column(name = "transactionid")
    String transactionId = UUID.randomUUID().toString();

    @Column(name = "debitaccountnumber")
    String debitAccountNumber;

    @Column(name = "creditaccountnumber")
    String creditAccountNumber;

    @Column(name = "balance")
    BigDecimal amount;

    @Column(name = "transactiontype")
    TRANSACTION_TYPE transactionType;

    @Column(name = "transactionmethod")
    TRANSACTION_METHOD transactionMethod;
}
