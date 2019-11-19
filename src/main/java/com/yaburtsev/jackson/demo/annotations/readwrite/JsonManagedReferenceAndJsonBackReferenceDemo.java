package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonManagedReferenceAndJsonBackReferenceDemo {

    public static void main(String[] args) throws IOException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book");
        user.addItem(item);

        final ObjectMapper mapper = new ObjectMapper();

        final String itemJson = mapper.writeValueAsString(item);
        System.out.println(itemJson);

        final String userJson = mapper.writeValueAsString(user);
        System.out.println(userJson);

        System.out.println(mapper.readValue(itemJson, ItemWithRef.class));
        System.out.println(mapper.readValue(userJson, UserWithRef.class));
    }

    private static class ItemWithRef {

        public int id;
        public String itemName;

        @JsonBackReference
        public UserWithRef owner;

        public ItemWithRef() {

        }

        public ItemWithRef(int id, String itemName) {
            this.id = id;
            this.itemName = itemName;
        }

        @Override
        public String toString() {
            return "ItemWithRef{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", ownerId=" + Optional.ofNullable(owner)
                .map(owner -> owner.id)
                .orElse(null) +
                '}';
        }
    }

    private static class UserWithRef {

        public int id;
        public String name;

        @JsonManagedReference
        public List<ItemWithRef> userItems = new ArrayList<>();

        public UserWithRef() {

        }

        public UserWithRef(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void addItem(ItemWithRef itemWithRef) {
            itemWithRef.owner = this;
            userItems.add(itemWithRef);
        }

        @Override
        public String toString() {
            return "UserWithRef{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userItems=" + userItems +
                '}';
        }
    }
}
