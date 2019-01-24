package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService
{
    Address save(Address address);

    Page<Address> findAll(Pageable pageable);

    Address findById(Long id);

    void delete(Address address);
}