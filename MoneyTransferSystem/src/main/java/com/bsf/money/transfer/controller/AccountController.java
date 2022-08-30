package com.bsf.money.transfer.controller;

import com.bsf.money.transfer.model.Account;
import com.bsf.money.transfer.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountNumber}")
    ResponseEntity<Account> getAccountByAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByAccountNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/transferAmount")
    String amountTransfer(@RequestParam String debitAccount,
                          @RequestParam String creditAccount,
                          @RequestParam BigDecimal amount) {
        accountService.amountTransfer(debitAccount, creditAccount, amount);
        return "Success";
    }
}
