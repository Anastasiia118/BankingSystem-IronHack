package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import com.ironHacking.bankingSystem.repositories.AdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AdminsService {

    @Autowired
    AdminsRepository adminsRepository;

    @Autowired
    AccountRepository accountRepository;
    public BigDecimal checkBalance(Long id) {
        Account acc;
        BigDecimal balance;
        if(accountRepository.findById(id).isPresent()){
           acc =  accountRepository.findById(id).get();
           balance = acc.getBalance();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account with this ID doesn't exits in the database!");
        }
        return balance;

    }

    public void modifyBalance(Long id, BigDecimal amount) {
        if (accountRepository.findById(id).isPresent()) {
            Account account = accountRepository.findById(id).get();
            account.setBalance(amount);
            accountRepository.save(account);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The ID introduced doesn't match any Accounts in our database.");
        }
    }
}
