package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findById(Long id);
}