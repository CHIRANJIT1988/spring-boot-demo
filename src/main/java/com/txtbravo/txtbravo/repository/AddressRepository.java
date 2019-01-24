package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findById(Long id);
}