package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JsonFilterDemo {

    static final String MY_FILTER = "myFilter";

    public static void main(String[] args) throws JsonProcessingException {
        BeanWithFilter bean = new BeanWithFilter(1, "My bean");

        FilterProvider filters = new SimpleFilterProvider()
            .addFilter(MY_FILTER, SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        String result = new ObjectMapper()
            .writer(filters)
            .writeValueAsString(bean);

        System.out.println(result);
    }

    @JsonFilter(MY_FILTER)
    private static class BeanWithFilter {

        public int id;
        public String name;

        public BeanWithFilter(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
