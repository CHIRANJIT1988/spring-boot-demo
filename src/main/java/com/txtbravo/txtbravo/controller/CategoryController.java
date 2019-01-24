package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.entity.Category;
import com.txtbravo.txtbravo.service.CategoryService;
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
@RequestMapping("/rest/category/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Category> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Long id)
    {
        Category category = categoryService.findById(id);

        if(category == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(category);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody Category category, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            return categoryService.save(category);
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Category _category, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            Category category = categoryService.findById(id);

            if(category == null)
            {
                return ResponseEntity.notFound().build();
            }

            category.setCategoryName(_category.getCategoryName());

            return ResponseEntity.ok().body(categoryService.save(category));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Category> delete(@PathVariable(value = "id") Long id)
    {
        Category category = categoryService.findById(id);

        if(category == null)
        {
            return ResponseEntity.notFound().build();
        }

        categoryService.delete(category);

        return ResponseEntity.ok().body(category);
    }
}