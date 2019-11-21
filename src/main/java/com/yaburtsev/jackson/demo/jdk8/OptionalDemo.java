package com.yaburtsev.jackson.demo.jdk8;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());

        System.out.println("Serializing ...");

        Contact nullEmail = new Contact("Example Co.", null);
        String nullEmailJson = mapper.writeValueAsString(nullEmail);
        // prints: {"name":"Example Co.","email":null}
        System.out.println(nullEmailJson);

        Contact emptyEmail = new Contact("Example Co.", Optional.empty());
        String emptyEmailJson = mapper.writeValueAsString(emptyEmail);
        // prints: {"name":"Example Co.","email":null}
        System.out.println(emptyEmailJson);

        Contact withEmail = new Contact("Example Co.", Optional.of("info@example.com"));
        String withEmailJson = mapper.writeValueAsString(withEmail);
        // prints:  {"name":"Example Co.","email":"info@example.com"}
        System.out.println(withEmailJson);

        System.out.println("\nDeserializing ...");
        System.out.println(mapper.readValue("{\"name\":\"Example Co.\"}", Contact.class));
        System.out.println(mapper.readValue("{\"name\":\"Example Co.\",\"email\":null}", Contact.class));
        System.out.println(mapper.readValue("{\"name\":\"Example Co.\",\"email\":\"info@example.com\"}", Contact.class));
    }

    private static class Contact {

        private final String name;
        private final Optional<String> email;

        @JsonCreator
        public Contact(@JsonProperty("name") String name, @JsonProperty("email") Optional<String> email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Contact{" +
                "name='" + name + '\'' +
                ", email=" + email +
                '}';
        }
    }
}
