package com.jpa.amazon.ecommerce.accounts.service;

import com.jpa.amazon.ecommerce.accounts.domain.Account;
import com.jpa.amazon.ecommerce.accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Iterable<Account> getAll(){
        return accountRepository.findAll();
    }

    public Account getById(Long id){
        return accountRepository.findById(id).get();
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Long id, Account account){
        Account accountById = accountRepository.findById(id).get();
        accountById.setFirstName(account.getFirstName());
        accountById.setLastName(account.getLastName());
        accountById.setEmailAddress(account.getEmailAddress());
        accountById.setAddresses(account.getAddresses());
        return accountRepository.save(accountById);
    }

    public void delete(Long id){
        accountRepository.deleteById(id);
    }
}
