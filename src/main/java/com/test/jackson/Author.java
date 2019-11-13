package com.test.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

    private final String firstName;
    private final String lastName;

    @JsonCreator
    public Author(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
