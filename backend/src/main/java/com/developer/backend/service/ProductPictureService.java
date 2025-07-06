package com.developer.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.developer.backend.entity.Product;
import com.developer.backend.entity.ProductPicture;
import com.developer.backend.repository.ProductPictureRepository;
import com.developer.backend.repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductPictureService {

  
    private final ProductPictureRepository productPictureRepository;
    private final ProductRepository productRepository; 

    @Autowired
    public ProductPictureService(ProductPictureRepository productPictureRepository, 
                                 ProductRepository productRepository) { // Inject ProductRepository
        this.productPictureRepository = productPictureRepository;
        this.productRepository = productRepository;
    }

    
    //@Async
    //public ProductPicture save(MultipartFile file, Long productId) throws IOException {

    public synchronized Map<String, String> save(MultipartFile file, Long productId) throws IOException {
        /* Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId)); */

        ProductPicture productPicture = new ProductPicture(
            file.getBytes(),
            file.getContentType(),
            productId

            );
        System.out.println("pricture: "+file.getBytes());
        System.out.println("fileContentype: "+file.getContentType());
        System.out.println("productId: "+productId);
       /*  productPicture.setId(14l);
        productPicture.setFile(file.getBytes());
        productPicture.setFileContentType(file.getContentType());
        productPicture.setLastModifiedDate(LocalDateTime.now());
        productPicture.setProduct(productId); */
        
        System.out.println("salio");

         Map<String, String> map = new HashMap<>();
        map.put("db", "oracle");
        map.put("username", "user1");
        map.put("password", "pass1");
        
        productPictureRepository.save(productPicture);
        return  map;
    }


    public Optional<ProductPicture> findById(Long id) {
        return productPictureRepository.findById(id);
    }

    public Optional<List<ProductPicture>> findAllPictures(Long id) {
        return productPictureRepository.findByProducts(id);
    }


    public List<ProductPicture> findAll() {
        return productPictureRepository.findAll();
    }

    /* public List<ProductPicture> findByProductId(Long productId) {
        return productPictureRepository.findByProductId(productId);
    } */

    @Transactional
    public void deleteById(Long id) {
        productPictureRepository.deleteById(id);
    }
}

