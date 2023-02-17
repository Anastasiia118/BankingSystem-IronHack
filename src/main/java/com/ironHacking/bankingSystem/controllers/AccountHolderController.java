package com.ironHacking.bankingSystem.controllers;

import com.ironHacking.bankingSystem.models.utilities.Transfer;
import com.ironHacking.bankingSystem.services.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    @PostMapping("/makeTransaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeTransaction(@RequestBody Transfer transaction){
        accountHolderService.transferMoney(transaction);
    }

    @GetMapping("/checkMyBalance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@PathVariable Long id, Authentication authentication){

        return accountHolderService.checkBalance(id, authentication);
    }
}
