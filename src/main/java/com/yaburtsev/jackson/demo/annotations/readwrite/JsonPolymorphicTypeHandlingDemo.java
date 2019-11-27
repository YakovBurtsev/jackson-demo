package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonPolymorphicTypeHandlingDemo {

    public static void main(String[] args) throws IOException {
        final var mapper = new ObjectMapper();

        Dog dog = new Dog("lacy");
        Zoo zoo1 = new Zoo(dog);
        System.out.println(mapper.writeValueAsString(zoo1));

        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";
        Zoo zoo2 = mapper.readerFor(Zoo.class).readValue(json);
        System.out.println(zoo2);
    }

    private static class Zoo {

        public Animal animal;

        public Zoo() {
        }

        public Zoo(Animal animal) {
            this.animal = animal;
        }

        @Override
        public String toString() {
            return "Zoo{" +
                "animal=" + animal +
                '}';
        }
    }

    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
    @JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    private static class Animal {

        public String name;

    }

    private static class Dog extends Animal {

        public Dog(String name) {
            this.name = name;
        }

        public double barkVolume;

        @Override
        public String toString() {
            return "Dog{" +
                "name='" + name + '\'' +
                ", barkVolume=" + barkVolume +
                '}';
        }
    }

    private static class Cat extends Animal {

        boolean likesCream;
        public int lives;

        public Cat() {
        }

        @Override
        public String toString() {
            return "Cat{" +
                "name='" + name + '\'' +
                ", likesCream=" + likesCream +
                ", lives=" + lives +
                '}';
        }
    }

}
