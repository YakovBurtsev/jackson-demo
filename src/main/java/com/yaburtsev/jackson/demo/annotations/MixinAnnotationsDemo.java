package com.yaburtsev.jackson.demo.annotations;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MixinAnnotationsDemo {

    public static void main(String[] args) throws JsonProcessingException {
        Item item = new Item(1, "book", null);

        String result = new ObjectMapper().writeValueAsString(item);
        System.out.println(result);

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(User.class, MyMixInForIgnoreType.class);

        result = mapper.writeValueAsString(item);
        System.out.println(result);
    }

    private static class Item {
        public int id;
        public String itemName;
        public User owner;

        public Item(int id, String itemName, User owner) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
        }
    }

    private static class User {
        public int id;
        public String userName;

    }

    @JsonIgnoreType
    private static class MyMixInForIgnoreType {}
}
