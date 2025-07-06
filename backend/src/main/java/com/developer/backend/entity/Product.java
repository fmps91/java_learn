package com.developer.backend.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL") 
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;

    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "size", length = 256)
    private String size;

    @Column(name = "color", length = 256)
    private String color;

    @Column(name = "brand", length = 256)
    private String brand;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "featured")
    private Boolean featured;

    @Column(name = "nb_in_stock")
    private Long nbInStock;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductPicture> pictures;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_fk", insertable = false, updatable = false)
    private ProductCategory categoryObj;

    @Column(name = "category_fk")
    private Long category;

   /*  @Column(name = "category_fk")
    private Long category; */


    public Product() {
    }


    public Product(Long id, UUID publicId, String name, Float price, String size, String color, String brand,
            String description, Boolean featured, Long nbInStock, LocalDateTime createdDate,
            Long category
            //ProductCategory category
            ) {
        this.id = id;
        this.publicId = publicId;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.description = description;
        this.featured = featured;
        this.nbInStock = nbInStock;
        this.createdDate = createdDate;
        this.category = category;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Long getNbInStock() {
        return nbInStock;
    }

    public void setNbInStock(Long nbInStock) {
        this.nbInStock = nbInStock;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        if (createdDate==null) {
            this.createdDate = LocalDateTime.now();    
        } else {
            this.createdDate = createdDate;
        }
        
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        
    }


    public List<ProductPicture> getPictures() {
        return pictures;
    }


    public void setPictures(List<ProductPicture> pictures) {
        this.pictures = pictures;
    }


    public ProductCategory getCategoryObj() {
        return categoryObj;
    }


    public void setCategoryObj(ProductCategory categoryObj) {
        this.categoryObj = categoryObj;
    }


    public Long getCategory() {
        return category;
    }


    public void setCategory(Long category) {
        this.category = category;
    }

    
}