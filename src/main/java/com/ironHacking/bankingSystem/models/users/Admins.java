package com.ironHacking.bankingSystem.models.users;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Admins extends User{

    public Admins() {
    }

    public Admins(String name, String username, String password, Collection<Role> roles) {
        super(null, name, username, password, roles);
    }

}
