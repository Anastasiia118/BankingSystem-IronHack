package com.ironHacking.bankingSystem.repositories;

import com.ironHacking.bankingSystem.models.users.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Long> {
}
