package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.accounts.CreditCard;
import com.ironHacking.bankingSystem.models.accounts.Savings;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    public CreditCard createCredit(AccountDTO accountDTO, Long id) {
        if (accountHolderRepository.findById(id).isPresent()){
            Long idAccount = accountDTO.getId();
            BigDecimal balance = accountDTO.getBalance();
            String secretKey = accountDTO.getSecretKey();
            AccountHolder accountHolder = accountHolderRepository.findById(id).get();
            AccountHolder accountHolder2 = null;
            if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
                accountHolder2 = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
            }

            BigDecimal interestRate = null;
            if(accountDTO.getInterestRate() != null){
                interestRate = accountDTO.getInterestRate();
            }

            BigDecimal creditLim = null;
            if(accountDTO.getCreditLimit() != null){
                creditLim = accountDTO.getCreditLimit();
            }
            //Savings(BigDecimal balance, String secretKey, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate)
            CreditCard creditAcc = new CreditCard(idAccount, balance, secretKey, accountHolder, accountHolder2, interestRate, creditLim);
            return creditCardRepository.save(creditAcc);

        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account Holder with this ID doesn't exits in the database!");
        }
    }
}
