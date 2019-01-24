package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService
{
    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    void delete(Product product);
}