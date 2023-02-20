package com.ironHacking.bankingSystem.models.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ironHacking.bankingSystem.models.utilities.Address;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class AccountHolder extends User {


    @NotNull
    private LocalDate dateOfBirth;

    @Embedded
    @NotNull
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "SECONDARY_NAME")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "SECONDARY_POSTAL_CODE")),
            @AttributeOverride(name = "city", column = @Column(name = "SECONDARY_CITY")),
            @AttributeOverride(name = "country", column = @Column(name = "SECONDARY_COUNTRY"))
    })
    private Address mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(Long id, String name, String username, String password, Collection<Role> roles, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(id, name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }
    /*public AccountHolder(String name, String username, String password, Collection<Role> roles, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(null, name, username, password, roles);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }*/

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


}
