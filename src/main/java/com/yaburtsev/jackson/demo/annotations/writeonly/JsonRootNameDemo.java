package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonRootNameDemo {

    public static void main(String[] args) throws JsonProcessingException {
        UserWithRoot user = new UserWithRoot(1, "John");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);
        System.out.println(result);
    }

    @JsonRootName(value = "user")
    private static class UserWithRoot {
        public int id;
        public String name;

        UserWithRoot(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
