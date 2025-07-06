package com.developer.backend.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority {
    
    @Id
    @Column(length = 50) // Especifica la longitud del campo
    private String name;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

   /*  @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
    name = "user_authority",
    joinColumns = {
    @JoinColumn(name = "user_id"),
    @JoinColumn(name = "user_id")
    },
    inverseJoinColumns = {@JoinColumn(name = "authority_name")}
    )
    private Set<Person> persons = new HashSet<>(); */

    /* @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
    name = "user_authority",
    joinColumns = {
    @JoinColumn(name = "authority_name")
    },
    inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<Person> persons = new HashSet<>(); */

    public Authority() {
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


    
    





    /* public Set<Person> getPersons() {
        return persons;
    }



    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
 */


    
}
