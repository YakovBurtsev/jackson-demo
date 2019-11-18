package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonManagedReferenceAndJsonBackReferenceDemo {

    public static void main(String[] args) throws JsonProcessingException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book");
        user.addItem(item);

        final ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(item));
        System.out.println(mapper.writeValueAsString(user));
    }

    private static class ItemWithRef {
        public int id;
        public String itemName;

        @JsonManagedReference
        public UserWithRef owner;

        public ItemWithRef(int id, String itemName) {
            this.id = id;
            this.itemName = itemName;
        }

    }

    private static class UserWithRef {
        public int id;
        public String name;


        @JsonBackReference
        public List<ItemWithRef> userItems = new ArrayList<>();

        public UserWithRef(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void addItem(ItemWithRef itemWithRef) {
            itemWithRef.owner = this;
            userItems.add(itemWithRef);
        }

    }
}
