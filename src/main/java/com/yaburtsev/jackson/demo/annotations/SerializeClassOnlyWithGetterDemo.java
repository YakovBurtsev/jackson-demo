package com.yaburtsev.jackson.demo.annotations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class SerializeClassOnlyWithGetterDemo {

    public static void main(String[] args) throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        //serialize
        final Event event = new Event();
        mapper.writeValue(System.out, event);
    }

    private static class Event {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        public Date getDate() {
            return new Date();
        }
    }
}
