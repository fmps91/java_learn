
package com.developer.backend.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGSERIAL") 
    private Long id;

    @Column(name = "public_id", unique = true, nullable = false)
    private UUID publicId;
    
    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address_street")
    private String address_street;

    @Column(name = "address_zip_code")
    private String address_zip_code;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_country")
    private String address_country;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;




    /* @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "authority_name")
    )
    private Set<Authority> authorities = new HashSet<>(); */

    

    public Person() {
    }

    

    public Person(
    Long id, 
    UUID publicId, 
    String firstname, 
    String lastname, 
    String email, 
    String address_street,
    String address_zip_code, 
    String addressCity, 
    String address_country, 
    String image_url,
    LocalDateTime createdDate, 
    String username,
    String password
            ) {
        this.id = id;
        this.publicId = publicId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address_street = address_street;
        this.address_zip_code = address_zip_code;
        this.addressCity = addressCity;
        this.address_country = address_country;
        this.image_url = image_url;
        this.createdDate = createdDate;
        this.password = password;
        this.username = username;
    }



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
        this.publicId = publicId;
    }



    public String getFirstname() {
        return firstname;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getAddress_street() {
        return address_street;
    }



    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }



    public String getAddress_zip_code() {
        return address_zip_code;
    }



    public void setAddress_zip_code(String address_zip_code) {
        this.address_zip_code = address_zip_code;
    }



    public String getAddressCity() {
        return addressCity;
    }



    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }



    public String getAddress_country() {
        return address_country;
    }



    public void setAddress_country(String address_country) {
        this.address_country = address_country;
    }



    public String getImage_url() {
        return image_url;
    }



    public void setImage_url(String image_url) {
        this.image_url = image_url;
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



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }



    public LocalDateTime getLastSeen() {
        return lastSeen;
    }



    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    



}