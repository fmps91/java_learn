package com.developer.backend.entity;

import java.io.Serializable;
import java.util.UUID;

public class OrderedProductId implements Serializable {
    private Long orderId;
    private UUID productId;
    

    // Getters, setters, equals y hashCode
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderedProductId that = (OrderedProductId) o;

        if (!orderId.equals(that.orderId)) return false;
        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        int result = orderId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }
}