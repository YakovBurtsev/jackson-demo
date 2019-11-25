package com.yaburtsev.jackson.demo.jdk8;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDemo {

    public static void main(String[] args) throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        // JSR-310
        mapper.registerModule(new JavaTimeModule());

        //deserialize
        final Event newYear = mapper.readValue("{\"name\":\"new year\",\"date\":\"31.12.2019\"}", Event.class);
        System.out.println(newYear);

        //serialize
        final Event event = new Event("birthday", LocalDate.now());
        mapper.writeValue(System.out, event);
    }

    private static class Event {
        public String name;


        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        public LocalDate date;

        public Event() {
        }

        public Event(String name, LocalDate date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Event{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
        }
    }
}
