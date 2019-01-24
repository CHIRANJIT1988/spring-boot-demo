package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findById(Long id);
}