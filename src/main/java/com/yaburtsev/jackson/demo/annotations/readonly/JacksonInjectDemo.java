package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonInjectDemo {

    public static void main(String[] args) throws IOException {
        String jsonContent =
            "{" +
                "   \"make\": \"Ford\"," +
                "   \"model\": \"F150\"," +
                "   \"year\": 2008" +
                "}";

        InjectableValues inject = new InjectableValues.Std()
            .addValue(String.class, "ford.com");

        Vehicle vehicle = new ObjectMapper()
            .reader(inject)
            .forType(Vehicle.class)
            .readValue(jsonContent);

        System.out.printf("Make %s, Model %s, Year %d, URL %s%n",
            vehicle.getMake(), vehicle.getModel(),
            vehicle.getYear(), vehicle.webURL);
    }

    private static class Vehicle {

        private String make, model;
        private int year;

        @JsonCreator
        Vehicle(@JsonProperty("make") String make,
                @JsonProperty("model") String model,
                @JsonProperty("year") int year) {
            this.make = make;
            this.model = model;
            this.year = year;
        }

        String getMake() {
            return make;
        }

        String getModel() {
            return model;
        }

        int getYear() {
            return year;
        }

        @JacksonInject
        String webURL;
    }

}
