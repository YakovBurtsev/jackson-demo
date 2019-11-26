package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

public class JsonNamingDemo {

    public static void main(String[] args) throws JsonProcessingException {
        final var mapper = new ObjectMapper();
        final var namingBean = new NamingBean(1, "name");
        System.out.println(mapper.writeValueAsString(namingBean));

        System.out.println(mapper.readValue("{\"id\":1,\"bean_name\":\"name\"}", NamingBean.class));
    }

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class NamingBean {

        private int id;
        private String beanName;

        public NamingBean() {
        }

        public NamingBean(int id, String beanName) {
            this.id = id;
            this.beanName = beanName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        @Override
        public String toString() {
            return "NamingBean{" +
                "id=" + id +
                ", beanName='" + beanName + '\'' +
                '}';
        }
    }
}
