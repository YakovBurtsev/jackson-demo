package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUnwrappedDemo {

    public static void main(String[] args) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();

        Name name = new Name("John", "Doe");
        UnwrappedUser user = new UnwrappedUser(1, name);

        System.out.println(mapper.writeValueAsString(user));

        String json = "{\n" +
            "    \"id\":1,\n" +
            "    \"firstName\":\"John\",\n" +
            "    \"lastName\":\"Doe\"\n" +
            "}";

        System.out.println(mapper.readValue(json, UnwrappedUser.class));

    }

    private static class UnwrappedUser {
        public int id;

        @JsonUnwrapped
        public Name name;

        public UnwrappedUser() {
        }

        public UnwrappedUser(int id, Name name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "UnwrappedUser{" +
                "id=" + id +
                ", name=" + name +
                '}';
        }
    }

    private static class Name {
        public String firstName;
        public String lastName;

        public Name() {
        }

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
        }
    }

}
