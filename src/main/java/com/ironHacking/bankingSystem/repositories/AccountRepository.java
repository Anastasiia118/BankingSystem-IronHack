package com.ironHacking.bankingSystem.repositories;

import com.ironHacking.bankingSystem.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
}
