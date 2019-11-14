package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonAnySetterDemo {

    public static void main(String[] args) throws IOException {
        String jsonContent =
            "{" +
                "   \"id\": 820787," +
                "   \"firstName\": \"Pierre\"," +
                "   \"lastName\": \"Francois\"" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        PropContainer pc = mapper.readValue(jsonContent, PropContainer.class);

        Iterator<Map.Entry<String, Object>> iter = pc.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            System.out.printf("Key: %s, Value: %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n");

        PropContainer2 pc2 = mapper.readValue(jsonContent, PropContainer2.class);

        Iterator<Map.Entry<String, Object>> iter2 = pc2.iterator();
        while (iter2.hasNext()) {
            Map.Entry<String, Object> entry = iter2.next();
            System.out.printf("Key: %s, Value: %s%n", entry.getKey(), entry.getValue());
        }

    }

    private static class PropContainer {

        private Map<String, Object> properties;

        PropContainer() {
            properties = new HashMap<>();
        }

        @JsonAnySetter
        void addProperty(String fieldName, Object value) {
            properties.put(fieldName, value);
        }

        Iterator<Map.Entry<String, Object>> iterator() {
            return properties.entrySet().iterator();
        }
    }

    private static class PropContainer2 {

        public String lastName;
        private Map<String, Object> properties;

        PropContainer2() {
            properties = new HashMap<>();
        }

        @JsonAnySetter
        void addProperty(String fieldName, Object value) {
            properties.put(fieldName, value);
        }

        Iterator<Map.Entry<String, Object>> iterator() {
            return properties.entrySet().iterator();
        }
    }

}
