package com.yaburtsev.jackson.demo.annotations.readwrite;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

public class JsonFormatDemo {

    public static void main(String[] args) throws IOException {

        final ObjectMapper mapper = new ObjectMapper();

        //deserialize
        final Event newYear = mapper.readValue("{\"name\":\"new year\",\"date\":\"31.12.2019\"}", Event.class);
        System.out.println(newYear);

        //serialize
        final Event event = new Event("birthday", new Date());
        mapper.writeValue(System.out, event);
    }

    private static class Event {
        public String name;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        public Date date;

        public Event() {
        }

        public Event(String name, Date date) {
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
