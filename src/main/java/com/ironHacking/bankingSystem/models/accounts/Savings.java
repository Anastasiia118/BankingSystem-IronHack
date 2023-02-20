package com.ironHacking.bankingSystem.models.accounts;

import com.ironHacking.bankingSystem.models.accounts.enums.Status;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;

@Entity
public class Savings extends Account{
    private BigDecimal minimumBalance = new BigDecimal("1000");


    @Column(precision = 19, scale = 4)
    private BigDecimal interestRate = new BigDecimal("0.0025", new MathContext(4));
    public Savings() {
    }

    public Savings(Long id, BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(id, balance, secretKey, primaryOwner, secondaryOwner);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if(minimumBalance == null){
            this.minimumBalance = BigDecimal.valueOf(1000);
        } else if(minimumBalance.compareTo(BigDecimal.valueOf(100)) < 0){
            this.minimumBalance = BigDecimal.valueOf(100);
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if(interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0) {

            this.interestRate = BigDecimal.valueOf(0.5);

        } else if (interestRate.compareTo(BigDecimal.valueOf(0)) < 0) {

            this.interestRate = interestRate;

        }
    }
}
