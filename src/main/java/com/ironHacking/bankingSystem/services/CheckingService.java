package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.accounts.Checking;
import com.ironHacking.bankingSystem.models.accounts.StudentChecking;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import com.ironHacking.bankingSystem.repositories.CheckingRepository;
import com.ironHacking.bankingSystem.repositories.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class CheckingService {
    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Account createChecking(AccountDTO accountDTO, Long id) {

        if (accountHolderRepository.findById(id).isPresent()){

            BigDecimal balance = accountDTO.getBalance();
            String secretKey = accountDTO.getSecretKey();
            AccountHolder accountHolder = accountHolderRepository.findById(id).get();
            AccountHolder accountHolder2 = null;
            if(accountDTO.getSecondaryOwnerId() != null && accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).isPresent()){
               accountHolder2 = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).get();
            }

            LocalDate birthDate = accountHolder.getDateOfBirth();
            LocalDate todayDate = LocalDate.now();
            Period age = Period.between(birthDate, todayDate);

            if( age.getYears() >= 24){
                Checking checkingAcc = new Checking(balance, secretKey, accountHolder, accountHolder2);
                return checkingRepository.save(checkingAcc);
            } else {
                StudentChecking studentAcc = new StudentChecking(balance, secretKey, accountHolder, accountHolder2);
                return studentCheckingRepository.save(studentAcc);
            }

        } else {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Account Holder with this ID doesn't exits in the database!");
        }


    }
}
