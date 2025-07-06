package com.developer.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "product_picture", schema = "ecommerce")
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL") 
    private Long id;

    //@Lob
    @Column(name = "file", nullable = false)
    private byte[] file;

    @Column(name = "file_content_type", nullable = false)
    private String fileContentType;

    @Column(name = "created_date", insertable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    /* @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_fk", nullable = false)
    private Product product; */

    @Column(name = "product_fk")
    private Long product;

    

    // Getters and setters

    public ProductPicture() {
    }

    

    public ProductPicture(byte[] file, String fileContentType
        , Long product
        ) {
        this.file = file;
        this.fileContentType = fileContentType;
        this.product = product;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
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



    /* public Product getProduct() {
        return product;
    }



    public void setProduct(Product product) {
        this.product = product;
    } */

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    // You might want to add a constructor, toString, equals, and hashCode methods
}
