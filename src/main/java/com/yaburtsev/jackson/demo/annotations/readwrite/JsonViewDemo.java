package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonViewDemo {

    public static void main(String[] args) throws IOException {
        Item item = new Item();
        item.id = 2;
        item.itemName = "book";
        item.ownerName = "John";

        final ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper
            .writerWithView(Views.Public.class)
            .writeValueAsString(item));

        System.out.println(mapper
            .writerWithView(Views.Internal.class)
            .writeValueAsString(item));

        final String json = "{\"id\":2,\"itemName\":\"book\",\"ownerName\":\"John\"}";

        System.out.println(mapper.readerWithView(Views.Public.class).forType(Item.class)
            .readValue(json).toString());

        System.out.println(mapper.readerWithView(Views.Internal.class).forType(Item.class)
            .readValue(json).toString());
    }

    private static class Views {

        public static class Public {

        }

        public static class Internal extends Public {

        }
    }

    private static class Item {

        @JsonView(Views.Public.class)
        public int id;

        @JsonView(Views.Public.class)
        public String itemName;

        @JsonView(Views.Internal.class)
        public String ownerName;

        @Override
        public String toString() {
            return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
        }
    }
}
