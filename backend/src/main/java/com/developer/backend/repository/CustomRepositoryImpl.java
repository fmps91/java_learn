package com.developer.backend.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Object findByDynamicColumnTest(String query) {
        query = "SELECT * FROM ecommerce.person WHERE id=1";
        
        return entityManager.createNativeQuery(query)
                //.getResultList();
                .getSingleResult();
    }

    @Override
    public Object findByDynamicColumn(Map<String, Object> params) {
        String query = "SELECT * FROM ecommerce.person WHERE "+params.get("col")+"="+params.get("value");
        
        return entityManager.createNativeQuery(query)
                .getSingleResult();
    }

    @Override
    public <T> T findBySQLClass(String query, Class<T> clazz) {
        //query = "SELECT * FROM ecommerce.person WHERE id=1";
        return (T) entityManager.createNativeQuery(query, clazz).getSingleResult();
    }

    @Override
    public <T> List<T> ListfindBySQLClass(String query, Class<T> clazz) {
        //query = "SELECT * FROM ecommerce.person where id>0 and id<60";
        
        return entityManager.createNativeQuery(query, clazz).getResultList();
    }


    @Override
    public String CountQuery(String query) {
        return (String) entityManager.createNativeQuery(query).getSingleResult().toString();
    }

    @Override
    public <T> Optional<List<T>> ListBySQLClass(String query, Class<T> clazz) {
        
        return Optional.of((List<T>) entityManager.createNativeQuery(query, clazz).getResultList());
    }



 
}

/* public List<Object> findByDynamicColumn(String column, String value) {
        String query = "SELECT * FROM person WHERE " + column + " = :value";
        return entityManager.createNativeQuery(query)
                .setParameter("value", value)
                .getResultList();
    } */

