package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonIdentityInfoDemo {

    public static void main(String[] args) throws IOException {
        UserWithIdentity user = new UserWithIdentity(1, "John");
        ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
        user.addItem(item);

        final ObjectMapper mapper = new ObjectMapper();

        String itemJson = mapper.writeValueAsString(item);
        System.out.println(itemJson);

        final String userJson = mapper.writeValueAsString(user);
        System.out.println(userJson);

        System.out.println(mapper.readValue(itemJson, ItemWithIdentity.class));
        System.out.println(mapper.readValue(userJson, UserWithIdentity.class));
    }

    @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
    private static class ItemWithIdentity {

        public int id;
        public String itemName;
        public UserWithIdentity owner;

        public ItemWithIdentity() {
        }

        public ItemWithIdentity(int id, String itemName, UserWithIdentity owner) {
            this.id = id;
            this.itemName = itemName;
            this.owner = owner;
        }

        @Override
        public String toString() {
            return "ItemWithIdentity{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                '}';
        }
    }

    @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
    private static class UserWithIdentity {

        public int id;
        public String name;
        public List<ItemWithIdentity> userItems = new ArrayList<>();

        public UserWithIdentity() {
        }

        public UserWithIdentity(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void addItem(ItemWithIdentity item) {
            userItems.add(item);
        }

        @Override
        public String toString() {
            return "UserWithIdentity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userItems=" + userItems +
                '}';
        }
    }
}
