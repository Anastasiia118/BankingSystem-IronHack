package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Savings;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class SavingsService {
    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    public void createSavings(AccountDTO accountDTO, Long id) {
        if (accountHolderRepository.findById(id).isPresent()){
            BigDecimal balance = accountDTO.getBalance();
            String secretKey = accountDTO.getSecretKey();
            AccountHolder accountHolder = accountHolderRepository.findById(id).get();
            AccountHolder accountHolder2 = null;
            if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
                accountHolder2 = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
            }
            BigDecimal  minBalance = null;
            if(accountDTO.getMinimumBalance() != null){
              minBalance = accountDTO.getMinimumBalance();
            }
            BigDecimal interestRate = null;
            if(accountDTO.getInterestRate() != null){
                interestRate = accountDTO.getInterestRate();
            }
            //Savings(BigDecimal balance, String secretKey, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate)
            Savings savingAcc = new Savings(balance, secretKey, accountHolder, accountHolder2, minBalance, interestRate);
            savingsRepository.save(savingAcc);

        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account Holder with this ID doesn't exits in the database!");
        }

    }
}
