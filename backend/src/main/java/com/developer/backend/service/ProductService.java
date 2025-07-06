package com.developer.backend.service;

import com.developer.backend.entity.Pagination;
import com.developer.backend.entity.Product;
import com.developer.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> sortAll(String query,Class<Product> clazz) {
        //Optional<?> listObjs=productRepository.ListBySQLClass(query, obj.getClass());

        List<Product> products=productRepository.ListfindBySQLClass(query, clazz);

        if (products.isEmpty()) {
			throw new RuntimeException("list of  products not found");
		}
         return products;
    }

    public String Count(String query) {
        return productRepository.CountQuery(query);
    }


    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}