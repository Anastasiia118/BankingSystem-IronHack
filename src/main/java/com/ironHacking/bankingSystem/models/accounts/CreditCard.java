package com.ironHacking.bankingSystem.models.accounts;

import com.ironHacking.bankingSystem.models.users.AccountHolder;
import jakarta.persistence.Entity;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class CreditCard extends Account{
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);
    private BigDecimal creditLimit = BigDecimal.valueOf(100);

    public CreditCard() {
    }



    public CreditCard(Long id, BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, BigDecimal creditLimit) {
        super(id, balance, secretKey, primaryOwner, secondaryOwner);
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        //CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
        if(interestRate == null || interestRate.compareTo(BigDecimal.valueOf(0.2)) > 0){
            this.interestRate = BigDecimal.valueOf(0.2);
        }else if(interestRate.compareTo(BigDecimal.valueOf(0.1)) < 0) {
            this.interestRate = BigDecimal.valueOf(0.1);
        } else {
            this.interestRate = interestRate;
        }
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        //CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
        if(creditLimit == null){
            this.creditLimit = BigDecimal.valueOf(100);
        } else if(creditLimit.compareTo(BigDecimal.valueOf(100)) < 0){
            this.creditLimit= BigDecimal.valueOf(100);
        } else if(creditLimit.compareTo(BigDecimal.valueOf(100000)) > 0){
            this.creditLimit= BigDecimal.valueOf(100000);
        } else {
            this.creditLimit = creditLimit;
        }
    }
}
