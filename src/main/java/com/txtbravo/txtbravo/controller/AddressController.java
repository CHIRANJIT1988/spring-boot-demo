package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.entity.Address;
import com.txtbravo.txtbravo.model.User;
import com.txtbravo.txtbravo.service.AddressService;
import com.txtbravo.txtbravo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/user/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Address> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return addressService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id)
    {
        Address address = addressService.findById(id);

        if(address == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody Address address, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            User user = userService.findById(address.getUser().getId());

            if(user != null)
            {
                return addressService.save(address);
            }

            return ResponseEntity.notFound().build();
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Address _address, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            Address address = addressService.findById(id);

            if(address == null)
            {
                return ResponseEntity.notFound().build();
            }

            //product.setProductName(_product.getProductName());
            //product.setProductDescription(_product.getProductDescription());

            return ResponseEntity.ok().body(addressService.save(address));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)
    {
        Address address = addressService.findById(id);

        if(address == null)
        {
            return ResponseEntity.notFound().build();
        }

        addressService.delete(address);

        return ResponseEntity.ok().body(address);
    }
}