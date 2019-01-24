package com.txtbravo.txtbravo.controller;

import com.txtbravo.txtbravo.config.ValidateModel;
import com.txtbravo.txtbravo.entity.Category;
import com.txtbravo.txtbravo.entity.Product;
import com.txtbravo.txtbravo.service.CategoryService;
import com.txtbravo.txtbravo.service.ProductService;
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
@RequestMapping("/rest/product/")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Product> findAll(@PageableDefault(size = 20) Pageable pageable)
    {
        return productService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id)
    {
        Product product = productService.findById(id);

        if(product == null)
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(product);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object save(@Valid @RequestBody Product product, BindingResult bindingResults)
    {
        if(!bindingResults.hasErrors())
        {
            Category category = categoryService.findById(product.getCategory().getId());
            product.setCategory(category);

            return productService.save(product);
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product _product, BindingResult bindingResults)
    {

        if(!bindingResults.hasErrors())
        {
            Product product = productService.findById(id);

            if(product == null)
            {
                return ResponseEntity.notFound().build();
            }

            product.setProductName(_product.getProductName());
            product.setProductDescription(_product.getProductDescription());

            return ResponseEntity.ok().body(productService.save(product));
        }

        return ValidateModel.getValidationErrors(bindingResults);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id)
    {
        Product product = productService.findById(id);

        if(product == null)
        {
            return ResponseEntity.notFound().build();
        }

        productService.delete(product);

        return ResponseEntity.ok().body(product);
    }
}