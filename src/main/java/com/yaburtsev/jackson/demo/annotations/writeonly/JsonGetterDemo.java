package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonGetterDemo {

    public static void main(String[] args) throws IOException {

        String jsonContent =
            "{" +
                "   \"id\": 820787," +
                "   \"firstName\": \"Pierre\"" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(jsonContent, Person.class);
        System.out.println(person);
        mapper.writeValue(new File("pierre.json"), person);
    }

    private static class Person {

        private int personID = 0;
        private String firstName = null;

        @JsonGetter("id")
        public int personID() {
            return personID;
        }

        public void setPersonID(int personID) {
            this.personID = personID;
        }

        @JsonGetter("firstName")
        public String firstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        @Override
        public String toString() {
            return personID + ": " + firstName;
        }
    }
}
