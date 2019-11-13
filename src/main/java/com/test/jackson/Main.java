package com.test.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Author author = new Author("Lev", "Tolstoy");
        Book book = new Book("War and Peace", author);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(System.out, book);
    }

}
