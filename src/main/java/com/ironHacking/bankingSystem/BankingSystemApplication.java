package com.ironHacking.bankingSystem;

import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.accounts.Checking;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.users.Admins;
import com.ironHacking.bankingSystem.models.users.Role;
import com.ironHacking.bankingSystem.models.utilities.Address;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import com.ironHacking.bankingSystem.repositories.AdminsRepository;
import com.ironHacking.bankingSystem.repositories.CheckingRepository;
import com.ironHacking.bankingSystem.services.AdminsService;
import com.ironHacking.bankingSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AdminsRepository adminsRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	CheckingRepository checkingRepository;

	@Autowired
	UserService userService;

	@Autowired
	AdminsService adminsService;

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.saveRole(new Role(null, "ROLE_ACCOUNT_HOLDER"));
		userService.saveRole(new Role(null, "ROLE_ADMIN"));

		//public Address(String address, Integer postalCode, String city, String country)

//		Address firstAddress = new Address("Ausias Marc", 4657, "Barcelona", "Spain");
//		Address secAddress = new Address("Carders", 46337, "Barcelona", "Spain");
//
//
//		//public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress)
//		AccountHolder owner1 = new AccountHolder("Ana", "userAna", passwordEncoder.encode("76567"), new ArrayList<>(), LocalDate.of(1991, 8, 18), firstAddress, firstAddress);
//		accountHolderRepository.save(owner1);
//		userService.addRoleToUser("userAna", "ROLE_ACCOUNT_HOLDER");
//
//		AccountHolder owner2 = new AccountHolder("Gim", "userGim", passwordEncoder.encode("76567"), new ArrayList<>(), LocalDate.of(1986, 9, 19), firstAddress, firstAddress);
//		accountHolderRepository.save(owner2);
//		userService.addRoleToUser("userGim", "ROLE_ACCOUNT_HOLDER");
//
//		Admins admin1 = new Admins("Hugh", "adminHugh", passwordEncoder.encode("76567"), new ArrayList<>());
//		adminsRepository.save(admin1);
//		userService.addRoleToUser("adminHugh", "ROLE_ADMIN");
//
//		//public Checking(BigDecimal balance, String secretKey, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee)
//
//		checkingRepository.save(new Checking(new BigDecimal("1700"), "secretAna", owner1, owner1));
//		checkingRepository.save(new Checking(new BigDecimal("500"), "secretGim", owner2, owner2));


	}





}
