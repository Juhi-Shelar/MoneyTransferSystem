package com.bsf.money.transfer.entities;

import com.bsf.money.transfer.enums.ACCOUNT_TYPE;
import com.bsf.money.transfer.enums.CURRENCY;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "accountid")
    private final String accountId = UUID.randomUUID().toString();

    @Column(name = "accountnumber")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "name")
    private String name;

    @Column(name = "accounttype")
    private ACCOUNT_TYPE accountType;

    @Column(name = "branch")
    private String branch;

    @Column(name = "currency")
    private CURRENCY currency;

    @Column(name = "accountopendate")
    private Date accountOpenDate;

    @Column(name = "created")
    private String created;

    @Column(name = "modified")
    private String modified;

    @Version
    private Long version;
}
