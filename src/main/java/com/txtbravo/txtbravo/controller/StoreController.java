package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.model.Store;
import com.txtbravo.txtbravo.service.RoleService;
import com.txtbravo.txtbravo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/store")
public class StoreController
{
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    StoreService storeService;

    @Autowired
    RoleService roleService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Store> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return storeService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable(value = "id") Long id)
    {
        Store store = storeService.findById(id);

        if(store == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(store);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody Store store, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            /**
             * BCrypt Password and store
             */
            store.setPassword(bCryptPasswordEncoder.encode(store.getPassword()));

            return storeService.save(store);
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Store _store, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            Store store = storeService.findById(id);

            if(store == null)
            {
                return ResponseEntity.notFound().build();
            }

            store.setFirstName(_store.getFirstName());
            store.setLastName(_store.getLastName());
            store.setMobileNo(_store.getMobileNo());
            store.setUsername(_store.getUsername());

            return ResponseEntity.ok().body(storeService.save(store));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Store> delete(@PathVariable(value = "id") Long id)
    {
        Store store = storeService.findById(id);

        if(store == null)
        {
            return ResponseEntity.notFound().build();
        }

        storeService.delete(store);

        return ResponseEntity.ok().body(store);
    }
}