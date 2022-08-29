package com.bsf.money.transfer.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id @Column(name = "accountid")
    String accountId = UUID.randomUUID().toString();

    @Column(name = "accountnumber")
    String accountNumber;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    //
//    @Column(name = "accountname")
//    String accountName;
//
//    @Column(name = "accounttype")
//    ACCOUNT_TYPE accountType;
//
//    @Column(name = "branch")
//    String branch;
//
//    @Column(name = "currency")
//    String currency;
//
//    @Column(name = "accountopendate")
//    LocalDate accountOpenDate;
//
//    @Column(name="created")
//    LocalDateTime created;
//
//    @Column(name = "modified")
//    LocalDateTime modified;
//
//    @Column(name = "balance")
//    BigDecimal balance;
}
