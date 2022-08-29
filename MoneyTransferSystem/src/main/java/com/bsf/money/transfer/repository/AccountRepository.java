package com.bsf.money.transfer.repository;

import com.bsf.money.transfer.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    Account getAccountByAccountNumber(String accountNumber);
}
