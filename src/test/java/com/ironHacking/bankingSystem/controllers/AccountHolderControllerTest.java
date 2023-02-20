
package com.ironHacking.bankingSystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Checking;
import com.ironHacking.bankingSystem.models.accounts.CreditCard;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.users.Admins;
import com.ironHacking.bankingSystem.models.users.Role;
import com.ironHacking.bankingSystem.models.utilities.Address;
import com.ironHacking.bankingSystem.models.utilities.Transfer;
import com.ironHacking.bankingSystem.repositories.*;
import com.ironHacking.bankingSystem.services.AdminsService;
import com.ironHacking.bankingSystem.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc; //simular las peticiones HTTP
    private final ObjectMapper objectMapper = new ObjectMapper(); // construir objetos de JSON a partir de clases de JAVA

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminsRepository adminsRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminsService adminsService;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
        userRepository.deleteAll();

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(((request, response, chain) -> {
            response.setCharacterEncoding("UTF-8");
            chain.doFilter(request, response);
        })).build();

        Address firstAddress = new Address("Ausias Marc", 4657, "Barcelona", "Spain");
        Address secAddress = new Address("Carders", 46337, "Barcelona", "Spain");

        AccountHolder owner1 = new AccountHolder(1L, "Ana", "userAna", "76567", new ArrayList<>(), LocalDate.of(1991, 8, 18), firstAddress, firstAddress);
        accountHolderRepository.save(owner1);
        AccountHolder owner2 = new AccountHolder(2L, "Gim", "userGim", "76567", new ArrayList<>(), LocalDate.of(1986, 9, 19), firstAddress, firstAddress);
        accountHolderRepository.save(owner2);

        Admins admin1 = new Admins(3L, "Hugh", "adminHugh", "76567", new ArrayList<>());
        adminsRepository.save(admin1);

        //Long id, BigDecimal balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal interestRate, BigDecimal creditLimit

        CreditCard credit1 = new CreditCard(129L, BigDecimal.valueOf(650), "secretCredit", owner2, owner2, BigDecimal.valueOf(0.4), BigDecimal.valueOf(500));
        creditCardRepository.save(credit1);
        checkingRepository.save(new Checking(1L, new BigDecimal("1700"), "secretAna", owner1, owner1));
        checkingRepository.save(new Checking(2L, new BigDecimal("500"), "secretGim", owner2, owner2));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void makeTransaction() throws Exception {

        Transfer transfer = new Transfer(1L, 2L, 1L, 2L, BigDecimal.valueOf(1000));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
       assertTrue(mvcResult.getResponse().getContentAsString().contains("700.00"));
    }

    @Test
    void makeTransactionWhenAccountDoesNotBelongToAccHolder() throws Exception {

        Transfer transfer = new Transfer(1L, 2L, 2L, 2L, BigDecimal.valueOf(400));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void makeTransactionWhenPenaltyFeeApplied() throws Exception {

        Transfer transfer = new Transfer(1L, 2L, 1L, 2L, BigDecimal.valueOf(1500));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("160.00"));
    }

    @Test
    void makeTransactionWhenAccHolderDoesNotExist() throws Exception {

        Transfer transfer = new Transfer(4L, 2L, 1L, 2L, BigDecimal.valueOf(1500));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    void makeTransactionWhenNotEnoughFounds() throws Exception {

        Transfer transfer = new Transfer(1L, 2L, 1L, 2L, BigDecimal.valueOf(1800));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable())
                .andReturn();

    }

    @Test // when after the transaction penalty fee should be applied and there are not enough funds
    void makeTransactionWhenNotEnoughFoundsForPenaltyFee() throws Exception {

        Transfer transfer = new Transfer(1L, 2L, 1L, 2L, BigDecimal.valueOf(1680));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotAcceptable())
                .andReturn();

    }

    @Test  //when make transaction from credit card but  there is no enough credit Limit
    void makeTransactionFromCreditWhenLimitExceeded() throws Exception {

        Transfer transfer = new Transfer(2L, 1L, 129L, 1L, BigDecimal.valueOf(1300));
        String body = objectMapper.writeValueAsString(transfer);
        MvcResult mvcResult = mockMvc.perform(post("/makeTransaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBandwidthLimitExceeded())
                .andReturn();

    }



   /* @Test  //does not work
    void checkBalance() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/checkMyBalance/1") .with(user("userA").password("76567"))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }*/
}
