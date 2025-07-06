package com.developer.backend.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_authority")
@IdClass(UserAuthorityId.class)
public class UserAuthority {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;



    public UserAuthority() {
    }

    public UserAuthority(Long userId, String authorityName) {
        this.userId = userId;
        this.authorityName = authorityName;
    }


    // Getters y setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
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



/* import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "user_authority")
@IdClass(UserAuthorityId.class)
public class UserAuthority {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person user;

    @Id
    @ManyToOne
    @JoinColumn(name = "authority_name")
    private Authority authority;

    // Constructor
    public UserAuthority() {
    }

    // Getters y setters
    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
} */