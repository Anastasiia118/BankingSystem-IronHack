package com.ironHacking.bankingSystem.controllers;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.users.ThirdParty;
import com.ironHacking.bankingSystem.models.utilities.Transfer;
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
    public Account createAccount(@PathVariable String accountType, @PathVariable String id, @RequestBody AccountDTO accountDTO) {

       return  accountService.createAccount(accountType, Long.valueOf(id), accountDTO);


    }
    @PostMapping("/create/3dParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdParty(@RequestBody ThirdParty thirdParty){
       return accountService.create3dParty(thirdParty);
    }


}
