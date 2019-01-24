package com.txtbravo.txtbravo.dao;


import com.txtbravo.txtbravo.entity.Address;
import com.txtbravo.txtbravo.repository.AddressRepository;
import com.txtbravo.txtbravo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address save(Address address)
    {
        return addressRepository.save(address);
    }

    @Override
    public Page<Address> findAll(Pageable pageable)
    {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Address findById(Long id)
    {
        return addressRepository.findById(id);
    }

    @Override
    public void delete(Address address)
    {
        addressRepository.delete(address);
    }
}