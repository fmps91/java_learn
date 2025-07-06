package com.developer.backend.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldsObjet {


    /* private Object getFields(Object object) {
        return object;
    } */

    public void setFields(Object object) {

        Map<String, String> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            
         
            field.setAccessible(true); // Permitir acceso a propiedades privadas
            try {
                Object value = field.get(object);
                System.out.println(field.getName() + ": " + value+"    type:  "+field.getType());
                map.put(field.getName(), ""+value);
                
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("ya los atributos");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            System.out.println("Key: " + k + ", Value: " + v);
        }


       //return 
    }
}
