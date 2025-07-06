package com.developer.backend.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomRepository {
    Object findByDynamicColumnTest(String query);
    Object findByDynamicColumn(Map<String, Object> params);
    <T> T findBySQLClass(String query, Class<T> clazz);
    <T> List<T> ListfindBySQLClass(String query, Class<T> clazz);
    <T> Optional<List<T>> ListBySQLClass(String query, Class<T> clazz);
    String CountQuery(String query);
}

