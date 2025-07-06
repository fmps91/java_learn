package com.developer.backend.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Map_hash {

    public static void iterateMap(Map<String, Object> map) {
        // este es un ejemplo para poder obtener mas objetos dentro del json ejemplo
        /*
         * {
         * "email": "a@14.com",
         * "direction": {
         * "calle": "EEUU y Guatemala",
         * "person": {
         *  "id":1,
         *  "name":"a"
         * }
         * 
         * }
         */
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            System.out.println("Clave: " + key + ", Valor: " + value);

            // Verificar si el valor es otro mapa (por ejemplo, "person")
            if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                System.out.println("Iterando sobre el mapa anidado:");
                iterateMap(nestedMap); // Llamada recursiva
            }
        }
    }

    public void ExampleMap() {
        Map<String, String> map = new HashMap<>();
        map.put("db", "oracle");
        map.put("username", "user1");
        map.put("password", "pass1");

        // Get keys and values
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            System.out.println("Key: " + k + ", Value: " + v);
        }

        // Get all keys
        Set<String> keys = map.keySet();
        for (String k : keys) {
            System.out.println("Key: " + k);
        }

        // Get all values
        Collection<String> values = map.values();
        for (String v : values) {
            System.out.println("Value: " + v);
        }

        // Java 8
        map.forEach((k, v) -> {
            System.out.println("Key: " + k + ", Value: " + v);
        });
    }
}
