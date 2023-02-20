package com.ironHacking.bankingSystem.repositories;

import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    AccountHolder findByUsername(String username);


}
