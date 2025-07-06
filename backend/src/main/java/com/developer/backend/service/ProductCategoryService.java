package com.developer.backend.service;

import com.developer.backend.entity.ProductCategory;
import com.developer.backend.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> findById(Long id) {
        return productCategoryRepository.findById(id);
    }

    public ProductCategory update(Long id, ProductCategory updatedProductCategory) {
        return productCategoryRepository.findById(id)
            .map(existingCategory -> {
                existingCategory.setName(updatedProductCategory.getName());
                return productCategoryRepository.save(existingCategory);
            })
            .orElseThrow(() -> new IllegalArgumentException("Product category not found with id " + id));
    }

    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public void deleteById(Long id) {
        productCategoryRepository.deleteById(id);
    }
}