package com.bsf.money.transfer.controller;

import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.repository.AccountRepository;
import com.bsf.money.transfer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    ResponseEntity<List<Account>> getAccounts(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(account1 -> accounts.add(account));
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
