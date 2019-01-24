package com.txtbravo.txtbravo.dao;


import com.txtbravo.txtbravo.entity.Product;
import com.txtbravo.txtbravo.repository.ProductRepository;
import com.txtbravo.txtbravo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product product)
    {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable)
    {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id)
    {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Product product)
    {
        productRepository.delete(product);
    }
}