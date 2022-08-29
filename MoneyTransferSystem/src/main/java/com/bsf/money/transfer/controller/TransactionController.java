package com.bsf.money.transfer.controller;

import com.bsf.money.transfer.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PutMapping("/amountTransfer")
    String amountTransfer(@RequestParam String debitAccount,
                          @RequestParam String creditAccount,
                          @RequestParam BigDecimal amount) {
        transactionService.amountTransfer(debitAccount, creditAccount, amount);
        return "Success";
    }
}
