package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

public class JsonAppendDemo {

    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();

        BeanWithoutAppend beanWithoutAppend = new BeanWithoutAppend(2, "Bean Without Append Annotation");

        System.out.println(mapper
            .writerFor(BeanWithoutAppend.class)
            .withAttribute("version", "1.0")
            .writeValueAsString(beanWithoutAppend));


        BeanWithAppend beanWithAppend = new BeanWithAppend(2, "Bean With Append Annotation");

        System.out.println(mapper
            .writerFor(BeanWithAppend.class)
            .withAttribute("version", "1.0")
            .writeValueAsString(beanWithAppend));
    }

    private static class BeanWithoutAppend {

        private int id;
        private String name;

        public BeanWithoutAppend(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    @JsonAppend(attrs = {
        @JsonAppend.Attr(value = "version")
    })
    private static class BeanWithAppend {

        private int id;
        private String name;

        public BeanWithAppend(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
