package com.bsf.money.transfer.repository;

import com.bsf.money.transfer.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Account getAccountByAccountNumber(String accountNumber);
}
