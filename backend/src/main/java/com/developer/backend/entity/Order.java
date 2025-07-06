package com.developer.backend.entity;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @Column(name = "status", nullable = false, length = 256)
    private String status;

    @Column(name = "stripe_session_id", nullable = false, length = 256)
    private String stripeSessionId;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /* @ManyToOne
    @JoinColumn(name = "fk_customer", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_order_id"), nullable = false)
    private Person customer; */

    @Column(name = "fk_customer")
    private Long customer;


    public Order() {
    }

    

    public Order(UUID publicId, String status, String stripeSessionId, LocalDateTime createdDate,
            LocalDateTime lastModifiedDate, Long customer) {
        this.publicId = publicId;
        this.status = status;
        this.stripeSessionId = stripeSessionId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.customer = customer;
    }


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        if (publicId == null) {
            this.publicId = UUID.randomUUID();
        } else {
            this.publicId = publicId;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStripeSessionId() {
        return stripeSessionId;
    }

    public void setStripeSessionId(String stripeSessionId) {
        this.stripeSessionId = stripeSessionId;
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



    public Long getCustomer() {
        return customer;
    }



    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    /* public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    } */
}