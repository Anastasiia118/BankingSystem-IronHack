package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.utilities.Transfer;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    public void transferMoney(Transfer transaction) {

        if(accountHolderRepository.findById(transaction.getIdSender()).isPresent()){
            AccountHolder sender = accountHolderRepository.findById(transaction.getIdSender()).get();


        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account Holder with this ID doesn't exits in the database!");
        }

        //AccountHolder sender = accountHolderRepository.findById(transaction.getIdSender());
    }

    public BigDecimal checkBalance(Long id, Authentication authentication) {
        Account acc;
        BigDecimal balance;
        if(accountRepository.findById(id).isPresent() && accountRepository.findById(id).get().getPrimaryOwner().getId() == accountHolderRepository.findByUsername(authentication.getPrincipal().toString()).getId()){
            acc =  accountRepository.findById(id).get();
            balance = acc.getBalance();
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account with this ID doesn't exits in the database!");
        }
        return balance;
    }
}
