package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StoreRepository extends JpaRepository<Store, Integer>
{
    Store findById(Long id);
    Store findByUsername(String username);
}