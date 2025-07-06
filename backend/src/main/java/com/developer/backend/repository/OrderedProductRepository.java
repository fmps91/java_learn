

package com.developer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developer.backend.entity.OrderedProduct;
import com.developer.backend.entity.OrderedProductId;


@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {
}
