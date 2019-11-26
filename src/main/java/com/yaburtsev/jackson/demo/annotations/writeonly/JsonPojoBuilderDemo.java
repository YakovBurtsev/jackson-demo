package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class JsonPojoBuilderDemo {

    public static void main(String[] args) throws JsonProcessingException {
        final var mapper = new ObjectMapper();

        String jsonString = "{\"id\":5,\"name\":\"POJO Builder Bean\"}";

        final var bean = mapper.readValue(jsonString, Bean.class);
        System.out.println(bean);
    }

    @JsonDeserialize(builder = Builder.class)
    private static class Bean {

        private int identity;
        private String beanName;

        private Bean(Builder builder) {
            this.identity = builder.idValue;
            this.beanName = builder.nameValue;
        }

        @Override
        public String toString() {
            return "Bean{" +
                "identity=" + identity +
                ", beanName='" + beanName + '\'' +
                '}';
        }
    }

    @JsonPOJOBuilder(buildMethodName = "createBean", withPrefix = "construct")
    private static class Builder {

        private int idValue;
        private String nameValue;

        public Builder constructId(int id) {
            idValue = id;
            return this;
        }

        public Builder constructName(String name) {
            nameValue = name;
            return this;
        }

        public Bean createBean() {
            return new Bean(this);
        }
    }

}
