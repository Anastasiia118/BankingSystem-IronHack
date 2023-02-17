package com.ironHacking.bankingSystem.controllers;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/create/{accountType}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@PathVariable String accountType, @PathVariable String id, @RequestBody AccountDTO accountDTO) {

        accountService.createAccount(accountType, Long.valueOf(id), accountDTO);

    }
}
