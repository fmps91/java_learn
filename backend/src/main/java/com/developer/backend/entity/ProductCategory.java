package com.developer.backend.entity;


import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL") 
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    

    public ProductCategory() {
    }

    

    public ProductCategory(UUID publicId, String name, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.publicId = publicId;
        this.name = name;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
