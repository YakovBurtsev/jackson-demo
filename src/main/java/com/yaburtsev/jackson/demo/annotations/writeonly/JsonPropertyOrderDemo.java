package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import static java.lang.System.out;

public class JsonPropertyOrderDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        SomeClass1 sc1 = new SomeClass1();
        mapper.writeValue(new File("order1.json"), sc1);

        SomeClass2 sc2 = new SomeClass2();
        mapper.writeValue(new File("order2.json"), sc2);

        out.println("serialization successful");
    }

    private static class SomeClass1 {

        public int a = 1, b = 2, c = 3, d = 4;
        public String e = "e";
    }

    @JsonPropertyOrder({"e", "d", "c", "b", "a"})
    private static class SomeClass2 {

        public int a = 1, b = 2, c = 3, d = 4;
        public String e = "e";
    }
}
