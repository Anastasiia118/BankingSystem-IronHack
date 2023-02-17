package com.ironHacking.bankingSystem.controllers;

import com.ironHacking.bankingSystem.services.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AdminController {
    @Autowired
    AdminsService adminsService;

    @GetMapping("/balance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable Long id){
       return adminsService.checkBalance(id);
    }

    @PatchMapping("/changeBalance/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeBalance(@PathVariable Long id, @RequestParam BigDecimal amount){
        adminsService.modifyBalance(id,amount);
    }
}
