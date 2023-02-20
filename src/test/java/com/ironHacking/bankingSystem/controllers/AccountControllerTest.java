
package com.ironHacking.bankingSystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironHacking.bankingSystem.models.AccountDTO;
import com.ironHacking.bankingSystem.models.accounts.Checking;
import com.ironHacking.bankingSystem.models.users.AccountHolder;
import com.ironHacking.bankingSystem.models.users.Admins;
import com.ironHacking.bankingSystem.models.users.ThirdParty;
import com.ironHacking.bankingSystem.models.utilities.Address;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
class AccountControllerTest {

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
    private CheckingRepository checkingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminsService adminsService;

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

        checkingRepository.save(new Checking(1L,new BigDecimal("1700"), "secretAna", owner1, owner1));
        checkingRepository.save(new Checking(2L, new BigDecimal("500"), "secretGim", owner2, owner2));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void createAccount() throws Exception {

        AccountDTO accountDTO = new AccountDTO(3L, BigDecimal.valueOf(15000), "secKey", 1L);
        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult mvcResult = mockMvc.perform(post("/create/checking/1").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn();
        System.err.println(mvcResult.getResponse().getContentAsString());

       assertTrue(mvcResult.getResponse().getContentAsString().contains("secKey"));
    }
    @Test
    void createAccount2() throws Exception {

        AccountDTO accountDTO = new AccountDTO(3L, BigDecimal.valueOf(15000), "secKey", 1L);
        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult mvcResult = mockMvc.perform(post("/create/checkjhghj/1").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
                .andReturn();
        //System.err.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    void createThirdParty() throws Exception {

        ThirdParty thirdParty = new ThirdParty("tercero", "3hashedKey");
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(post("/create/3dParty").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();


        //System.err.println(mvcResult.getResponse().getContentAsString()); (to check)
        assertTrue(mvcResult.getResponse().getContentAsString().contains("3hashedKey"));
    }
    @Test
    void createThirdPartyWithoutParams() throws Exception {

        ThirdParty thirdParty = new ThirdParty();
        String body = objectMapper.writeValueAsString(thirdParty);
        MvcResult mvcResult = mockMvc.perform(post("/create/3dParty").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();


        //System.err.println(mvcResult.getResponse().getContentAsString()); (to check)
       // assertTrue(mvcResult.getResponse().getContentAsString().contains("3hashedKey"));
    }
}
