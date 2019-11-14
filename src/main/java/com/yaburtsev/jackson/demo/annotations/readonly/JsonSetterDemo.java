package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonSetterDemo {

    public static void main(String[] args) throws IOException {
        String jsonContent =
            "{" +
                "   \"id\": 820787," +
                "   \"firstName\": \"Pierre\"" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Person person = mapper.readValue(jsonContent, Person.class);
        System.out.println(person);

        Person2 person2 = mapper.readValue(jsonContent, Person2.class);
        System.out.println(person2);
    }

    private static class Person {

        private int personID = 0;
        private String firstName = null;

        public int getPersonID() {
            return personID;
        }

        @JsonSetter("id")
        public void setPersonID(int personID) {
            this.personID = personID;
        }

        public String getFirstName() {
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

    private static class Person2 {

        @JsonSetter("id")
        private int personID = 0;
        private String firstName = null;

        public String getFirstName() {
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
