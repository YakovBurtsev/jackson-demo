package com.yaburtsev.jackson.demo.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

public class CustomAnnotationDemo {

    public static void main(String[] args) throws JsonProcessingException {
        BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(1, "My bean", null);

        String result = new ObjectMapper().writeValueAsString(bean);
        System.out.println(result);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @JacksonAnnotationsInside
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({"name", "id", "dateCreated" })
    private @interface CustomAnnotation {}

    @CustomAnnotation
    private static class BeanWithCustomAnnotation {
        public int id;
        public String name;
        public Date dateCreated;

        public BeanWithCustomAnnotation(int id, String name, Date dateCreated) {
            this.id = id;
            this.name = name;
            this.dateCreated = dateCreated;
        }
    }
}
