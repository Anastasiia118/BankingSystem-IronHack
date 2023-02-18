package com.ironHacking.bankingSystem.services;

import com.ironHacking.bankingSystem.models.accounts.*;
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
import java.util.Objects;

@Service
public class AccountHolderService {
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    public void transferMoney(Transfer transaction) {

        if(accountHolderRepository.findById(transaction.getIdSender()).isPresent()){
            AccountHolder sender = accountHolderRepository.findById(transaction.getIdSender()).get();
            if(accountRepository.findById(transaction.getIdSenderAccount()).isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account Sender with this ID doesn't exits!");
            } else {

                Account senderAcc = accountRepository.findById(transaction.getIdSenderAccount()).get();


                if(Objects.equals(senderAcc.getPrimaryOwner().getId(), sender.getId())){

                    BigDecimal senderBalance = senderAcc.getBalance();
                    BigDecimal amountToSend = transaction.getAmount();
                    BigDecimal balanceLeft = senderBalance.subtract(amountToSend);
                    if(accountRepository.findById(transaction.getIdReceiver()).isEmpty()){
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account Receiver with this ID doesn't exits!");
                    }
                    Account accReceiver = accountRepository.findById(transaction.getIdReceiver()).get();
                    BigDecimal receiverBalance = accReceiver.getBalance();

                    if( balanceLeft.signum() >= 0 ) {
                        if(senderAcc instanceof Checking){
                            BigDecimal minBalance = ((Checking) senderAcc).getMinimumBalance();
                            if(balanceLeft.subtract(senderAcc.getPenaltyFee()).signum() < 0){
                                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                                        "After the transaction penalty fee should be applied and there is not enough funds");
                            } else if(balanceLeft.compareTo(minBalance) < 0){
                                BigDecimal balanceAfterFee = balanceLeft.subtract(senderAcc.getPenaltyFee());
                                senderAcc.setBalance(balanceAfterFee);
                                accountRepository.save(senderAcc);
                                accReceiver.setBalance(receiverBalance.add(amountToSend));
                                accountRepository.save(accReceiver);
                            } else {
                                senderAcc.setBalance(balanceLeft);
                                accountRepository.save(senderAcc);
                                accReceiver.setBalance(receiverBalance.add(amountToSend));
                                accountRepository.save(accReceiver);
                            }

                        } else if (senderAcc instanceof Savings) {
                            BigDecimal minBalance = ((Savings) senderAcc).getMinimumBalance();
                            if(balanceLeft.subtract(senderAcc.getPenaltyFee()).signum() < 0){
                                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                                        "After the transaction penalty fee should be applied and there is not enough funds");
                            } else if(balanceLeft.compareTo(minBalance) < 0){
                                BigDecimal balanceAfterFee = balanceLeft.subtract(senderAcc.getPenaltyFee());
                                senderAcc.setBalance(balanceAfterFee);
                                accountRepository.save(senderAcc);
                                accReceiver.setBalance(receiverBalance.add(amountToSend));
                                accountRepository.save(accReceiver);
                            } else {
                                senderAcc.setBalance(balanceLeft);
                                accountRepository.save(senderAcc);
                                accReceiver.setBalance(receiverBalance.add(amountToSend));
                                accountRepository.save(accReceiver);
                            }
                        } else {
                            senderAcc.setBalance(balanceLeft);
                            accountRepository.save(senderAcc);
                            accReceiver.setBalance(receiverBalance.add(amountToSend));
                            accountRepository.save(accReceiver);
                        }
                    } else if( senderAcc instanceof CreditCard){
                       BigDecimal creditLeft = ((CreditCard) senderAcc).getCreditLimit().subtract(balanceLeft.abs());
                       if(creditLeft.signum() < 0){
                           throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                                   "Limit is exceeded!");
                       } else{
                           senderAcc.setBalance(creditLeft.negate());
                           accountRepository.save(senderAcc);
                           accReceiver.setBalance(receiverBalance.add(amountToSend));
                           accountRepository.save(accReceiver);
                       }
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                                "Not enough funds!");
                    }

                } else{
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "The account does not belong this person");
                }
            }

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
