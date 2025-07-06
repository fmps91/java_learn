package com.developer.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developer.backend.entity.ProductPicture;

@Repository
public interface ProductPictureRepository extends JpaRepository<ProductPicture, Long> {

    
    @Query(value = "SELECT * FROM ecommerce.product_picture p where p.product_fk = :id ", nativeQuery = true)
    Optional<List<ProductPicture>> findByProducts(Long id);
}