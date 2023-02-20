//package com.ironHacking.bankingSystem;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.ironHacking.bankingSystem.models.AccountDTO;
//import com.ironHacking.bankingSystem.models.accounts.Checking;
//import com.ironHacking.bankingSystem.models.users.AccountHolder;
//
//import com.ironHacking.bankingSystem.models.users.Admins;
//import com.ironHacking.bankingSystem.models.utilities.Address;
//import com.ironHacking.bankingSystem.repositories.*;
//import com.ironHacking.bankingSystem.services.AdminsService;
//import com.ironHacking.bankingSystem.services.UserService;
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//@SpringBootTest
//class BankingSystemApplicationTests {
//
//
//	/*@PostMapping("/create/{accountType}/{id}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void createAccount(@PathVariable String accountType, @PathVariable String id, @RequestBody AccountDTO accountDTO) {
//
//		accountService.createAccount(accountType, Long.valueOf(id), accountDTO);
//
//	}*/
//
//	@Test
//	void contextLoads() {
//	}
//
//}
