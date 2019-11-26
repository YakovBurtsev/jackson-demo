package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonIdentityReferenceDemo {

    public static void main(String[] args) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();

        BeanWithoutIdentityReference beanWithoutIdRef = new BeanWithoutIdentityReference();
        beanWithoutIdRef.setId(1);
        beanWithoutIdRef.setName("Bean Without Identity Reference Annotation");

        System.out.println(mapper.writeValueAsString(beanWithoutIdRef));


        BeanWithIdentityReference beanWithIdRef = new BeanWithIdentityReference();
        beanWithIdRef.setId(1);
        beanWithIdRef.setName("Bean With Identity Reference Annotation");

        System.out.println(mapper.writeValueAsString(List.of(beanWithIdRef, beanWithIdRef)));

        final var list = mapper.readValue("[{\"id\":1,\"name\":\"Bean With Identity Reference Annotation\"},1]",
            new TypeReference<List<BeanWithIdentityReference>>() {
            });
        System.out.println(list.get(0));
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private static class BeanWithoutIdentityReference {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonIdentityReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private static class BeanWithIdentityReference {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "BeanWithIdentityReference{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
        }
    }

}
