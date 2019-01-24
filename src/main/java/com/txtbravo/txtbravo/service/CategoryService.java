package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService
{
    Category save(Category category);

    Page<Category> findAll(Pageable pageable);

    Category findById(Long id);

    void delete(Category category);
}