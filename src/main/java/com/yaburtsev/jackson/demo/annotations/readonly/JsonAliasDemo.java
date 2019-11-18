package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonAliasDemo {

    public static void main(String[] args) throws IOException {
        String json = "{\"fName\": \"John\", \"lastName\": \"Green\"}";
        AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
        System.out.println(aliasBean);
    }

    private static class AliasBean {

        private String firstName;
        private String lastName;

        @JsonAlias({"fName", "f_name"})
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "AliasBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
        }
    }
}
