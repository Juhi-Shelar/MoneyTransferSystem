package com.bsf.money.transfer.model;

import com.bsf.money.transfer.enums.ACCOUNT_TYPE;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account {
    private String accountNumber;
    private BigDecimal balance;
    private String name;
    private ACCOUNT_TYPE accountType;
    private String branch;
    private String currency;
    private Date accountOpenDate;
}
