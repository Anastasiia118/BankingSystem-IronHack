package com.ironHacking.bankingSystem.models.accounts;

import com.ironHacking.bankingSystem.models.accounts.enums.Status;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import jakarta.persistence.*;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Checking extends Account{


    private final BigDecimal minimumBalance = BigDecimal.valueOf(250);

    private final BigDecimal monthlyMaintenanceFee = BigDecimal.valueOf(12);

    public Checking() {
    }

    public Checking(Long id, BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(id, balance, secretKey, primaryOwner, secondaryOwner);
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }


}
