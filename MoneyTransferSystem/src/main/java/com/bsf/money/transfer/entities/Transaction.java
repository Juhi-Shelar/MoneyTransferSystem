package com.bsf.money.transfer.entities;

import com.bsf.money.transfer.enums.TRANSACTION_METHOD;
import com.bsf.money.transfer.enums.TRANSACTION_TYPE;
import lombok.*;

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
@Builder
@Getter
@Setter
public class Transaction {
    @Id
    @Column(name = "transactionid")
    private String transactionId = UUID.randomUUID().toString();

    @Column(name = "debitaccountnumber")
    private String debitAccountNumber;

    @Column(name = "creditaccountnumber")
    private String creditAccountNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transactiontype")
    private TRANSACTION_TYPE transactionType;

    @Column(name = "transactionmethod")
    private TRANSACTION_METHOD transactionMethod;

    @Column(name = "transactiontimestamp")
    private String transactionTimeStamp;

    @Column(name = "created")
    private String created;

    @Column(name = "modified")
    private String modified;
}
