package com.jpa.amazon.ecommerce.accounts.repository;

import com.jpa.amazon.ecommerce.accounts.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByAccountId(Long id);
    Address getByIdAndAccountId(Long id, Long accountId);

}
