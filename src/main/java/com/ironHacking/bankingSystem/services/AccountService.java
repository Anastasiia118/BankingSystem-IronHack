package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.users.ThirdParty;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import com.ironHacking.bankingSystem.repositories.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CheckingService checkingService;

    @Autowired
    SavingsService savingsService;

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    public Account createAccount(String accountType, Long id, AccountDTO accountDTO) {

        switch (accountType) {
            case "checking" -> {
                return checkingService.createChecking(accountDTO, id);

            }
            case "savings" -> {
                return savingsService.createSavings(accountDTO, id);

            }
            case "credit" -> {
                return creditCardService.createCredit(accountDTO, id);

            }
            default -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type is not valid, please re-try.");
            }

        }

    }

    public ThirdParty create3dParty(ThirdParty thirdParty) {
        String name = thirdParty.getName();
        String hashKey = thirdParty.getHashedKey();
        if(name == null || hashKey == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name or hashKey is not provided!");
        }
        return thirdPartyRepository.save(new ThirdParty(name, hashKey));
    }
}
