package com.ironHacking.bankingSystem.models.users;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Admins extends User{

    public Admins() {
    }

    public Admins(Long id, String name, String username, String password, Collection<Role> roles) {
        super(id, name, username, password, roles);
    }
}
