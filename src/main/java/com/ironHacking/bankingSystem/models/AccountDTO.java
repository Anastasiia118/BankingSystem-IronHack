package com.ironHacking.bankingSystem.models;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class AccountDTO {
    private BigDecimal balance;

    private String secretKey;

    private Long secondaryOwnerId;

    @Column(precision = 19, scale = 4)
    private BigDecimal interestRate;
    private BigDecimal creditLimit;

    private BigDecimal minimumBalance;

    public AccountDTO(BigDecimal balance, String secretKey, Long secondaryOwnerId) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
