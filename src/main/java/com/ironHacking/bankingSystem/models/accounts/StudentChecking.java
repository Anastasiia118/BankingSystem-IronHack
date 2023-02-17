package com.ironHacking.bankingSystem.models.accounts;

import com.ironHacking.bankingSystem.models.users.AccountHolder;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class StudentChecking extends Account{

    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }

}
