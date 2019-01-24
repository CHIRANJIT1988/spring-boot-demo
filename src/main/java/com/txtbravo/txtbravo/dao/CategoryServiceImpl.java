package com.txtbravo.txtbravo.dao;


import com.txtbravo.txtbravo.entity.Category;
import com.txtbravo.txtbravo.repository.CategoryRepository;
import com.txtbravo.txtbravo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category save(Category category)
    {
        return categoryRepository.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable)
    {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id)
    {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Category category)
    {
        categoryRepository.delete(category);
    }
}