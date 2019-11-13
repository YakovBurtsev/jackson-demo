package com.test.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

    private final String title;
    private final Author author;

    @JsonCreator
    public Book(@JsonProperty("title") String title,
                @JsonProperty("author") Author author) {
        this.title = title;
        this.author = author;
    }
}
