package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonCreatorAndJsonPropertyDemo {

    public static void main(String[] args) throws IOException {
        String jsonContent =
            "{" +
                "   \"make\": \"Ford\"," +
                "   \"model\": \"F150\"," +
                "   \"year\": 2008" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Vehicle vehicle = mapper.readValue(jsonContent, Vehicle.class);

        System.out.printf("Make %s, Model %s, Year %d%n",
            vehicle.getMadeBy(),
            vehicle.getModel(),
            vehicle.getYear());
    }

    private static class Vehicle {

        private final String madeBy;
        private final String model;
        private final int year;

        @JsonCreator
        Vehicle(@JsonProperty("make") String madeBy,
                @JsonProperty("model") String model,
                @JsonProperty("year") int year) {
            this.madeBy = madeBy;
            this.model = model;
            this.year = year;
        }

        String getMadeBy() {
            return madeBy;
        }

        String getModel() {
            return model;
        }

        int getYear() {
            return year;
        }
    }

}
