package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.model.Store;
import com.txtbravo.txtbravo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreService
{
    Store save(Store store);

    Page<Store> findAll(Pageable pageable);

    Store findById(Long id);

    void delete(Store store);
}