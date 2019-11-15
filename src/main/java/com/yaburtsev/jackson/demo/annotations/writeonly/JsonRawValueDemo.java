package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import static java.lang.System.out;

public class JsonRawValueDemo {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Driver1 d1 = new Driver1();
        mapper.writeValue(new File("driver1.json"), d1);

        Driver2 d2 = new Driver2();
        mapper.writeValue(new File("driver2.json"), d2);

        out.println("serialization successful");
    }

    private static class Driver1 {

        public String name = "John Doe";

        public String vehicle = "{ \"make\": \"Ford\", " +
            "\"model\": \"F150\", " +
            "\"year\": 2008 }";
    }

    private static class Driver2 {

        public String name = "John Doe";

        @JsonRawValue
        public String vehicle = "{ \"make\": \"Ford\", " +
            "\"model\": \"F150\", " +
            "\"year\": 2008 }";
    }
}
