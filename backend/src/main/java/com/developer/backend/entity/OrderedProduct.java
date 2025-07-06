package com.developer.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ordered_product")
@IdClass(OrderedProductId.class)
public class OrderedProduct {

    @Id
    @Column(name = "fk_order", nullable = false)
    private Long orderId;

    @Id
    @Column(name = "fk_product", nullable = false)
    private UUID productId;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "product_name", nullable = false, length = 256)
    private String productName;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /* @ManyToOne
    @JoinColumn(name = "fk_order", referencedColumnName = "id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_ordered_order_constraint"))
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_product", referencedColumnName = "public_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_ordered_product_constraint"))
    private Product product; */

    
    // Getters y setters
    public Long getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /* public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    } */
}

