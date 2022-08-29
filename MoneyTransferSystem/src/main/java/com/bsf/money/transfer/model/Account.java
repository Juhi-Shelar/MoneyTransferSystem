package com.bsf.money.transfer.model;

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
public class Account {
    @Id @Column(name = "accountid")
    String accountId = UUID.randomUUID().toString();

    @Column(name = "accountnumber")
    String accountNumber;

    @Column(name = "balance")
    BigDecimal balance;

}
