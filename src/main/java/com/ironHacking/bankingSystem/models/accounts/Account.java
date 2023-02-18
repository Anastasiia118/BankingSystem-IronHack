package com.ironHacking.bankingSystem.models.accounts;

import com.ironHacking.bankingSystem.models.accounts.enums.Status;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import jakarta.persistence.*;


import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private BigDecimal balance;

    private String secretKey;

    @ManyToOne
    @NotNull
    private AccountHolder primaryOwner;

    @ManyToOne
    private AccountHolder secondaryOwner;

    private final BigDecimal penaltyFee = BigDecimal.valueOf(40);

    private LocalDate creationDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Account() {
    }

    public Account(BigDecimal balance, String secretKey, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.balance = balance;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public @NotNull AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
