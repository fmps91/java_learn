package com.developer.backend.entity;



import java.io.Serializable;
import java.util.Objects;

public class UserAuthorityId implements Serializable {
    private Long userId;
    private String authorityName;

    // Getters, setters, equals y hashCode
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthorityId that = (UserAuthorityId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(authorityName, that.authorityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, authorityName);
    }
}

/* import java.io.Serializable;


public class UserAuthorityId implements Serializable {
    
    private Person user;

    private Authority authority;


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