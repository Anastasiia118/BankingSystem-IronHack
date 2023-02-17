package com.ironHacking.bankingSystem;

import com.ironHacking.bankingSystem.models.accounts.Account;
import com.ironHacking.bankingSystem.models.accounts.Checking;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.utilities.Address;
import com.ironHacking.bankingSystem.repositories.AccountHolderRepository;
import com.ironHacking.bankingSystem.repositories.AccountRepository;
import com.ironHacking.bankingSystem.repositories.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	CheckingRepository checkingRepository;

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//public Address(String address, Integer postalCode, String city, String country)

		Address firstAddress = new Address("Ausias Marc", 4657, "Barcelona", "Spain");
		Address secAddress = new Address("Carders", 46337, "Barcelona", "Spain");


		//public AccountHolder(String name, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress)
		AccountHolder owner = new AccountHolder("Max", LocalDate.of(1984, 03, 02), firstAddress, firstAddress);
		accountHolderRepository.save(owner);
		AccountHolder owner2 = new AccountHolder("Sophia", LocalDate.of(2000, 8, 11), secAddress, secAddress);
		accountHolderRepository.save(owner2);

		//public Checking(BigDecimal balance, String secretKey, @NotNull AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee)

		checkingRepository.save(new Checking(new BigDecimal("1222"), "secretik", owner, owner));
	}





}
