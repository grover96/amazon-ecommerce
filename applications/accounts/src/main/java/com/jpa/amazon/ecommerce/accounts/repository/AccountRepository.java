package com.jpa.amazon.ecommerce.accounts.repository;

import com.jpa.amazon.ecommerce.accounts.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
