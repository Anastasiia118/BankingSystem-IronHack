package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.AccountDTO;
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

    public void createAccount(String accountType, Long id, AccountDTO accountDTO) {

        switch (accountType) {
            case "checking" -> {
                checkingService.createChecking(accountDTO, id);
                break;
            }
            case "savings" -> {
                savingsService.createSavings(accountDTO, id);
                break;
            }
            case "credit" -> {
                creditCardService.createCredit(accountDTO, id);
                break;
            }
            default -> {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The account type is not valid, please re-try.");
            }

        }
    }

    public void create3dParty(ThirdParty thirdParty) {
        String name = thirdParty.getName();
        String hashKey = thirdParty.getHashedKey();
        thirdPartyRepository.save(new ThirdParty(name, hashKey));
    }
}
