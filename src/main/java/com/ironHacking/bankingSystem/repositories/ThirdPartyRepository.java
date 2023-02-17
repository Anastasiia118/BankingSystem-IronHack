package com.ironHacking.bankingSystem.repositories;

import com.ironHacking.bankingSystem.models.users.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
