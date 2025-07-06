package com.developer.backend.service;

import com.developer.backend.entity.OrderedProduct;
import com.developer.backend.entity.OrderedProductId;
import com.developer.backend.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedProductService {

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    public List<OrderedProduct> findAll() {
        return orderedProductRepository.findAll();
    }

    public Optional<OrderedProduct> findById(OrderedProductId id) {
        return orderedProductRepository.findById(id);
    }

    public OrderedProduct save(OrderedProduct orderedProduct) {
        return orderedProductRepository.save(orderedProduct);
    }

    public void deleteById(OrderedProductId id) {
        orderedProductRepository.deleteById(id);
    }
}
