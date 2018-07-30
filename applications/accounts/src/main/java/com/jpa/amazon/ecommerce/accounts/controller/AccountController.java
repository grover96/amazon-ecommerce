package com.jpa.amazon.ecommerce.accounts.controller;

import com.jpa.amazon.ecommerce.accounts.domain.Account;
import com.jpa.amazon.ecommerce.accounts.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")

public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public Iterable<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Account getById(@PathVariable("id") Long id){
        return accountService.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Account create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody Account update(@PathVariable("id") Long id, @RequestBody Account account){
        return accountService.update(id, account);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        accountService.delete(id);
    }
}
