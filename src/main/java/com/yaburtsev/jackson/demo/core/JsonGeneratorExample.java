package com.yaburtsev.jackson.demo.core;


import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;

import static java.lang.System.out;

public class JsonGeneratorExample {

    public static void main(String[] args) throws Exception {
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(new File("out.json"), JsonEncoding.UTF8);

        generator.useDefaultPrettyPrinter();

        generator.writeStartObject();
        generator.writeStringField("firstName", "John");
        generator.writeStringField("lastName", "Doe");
        generator.writeNumberField("age", 42);
        generator.writeFieldName("address");
        generator.writeStartObject();
        generator.writeStringField("street", "400 Some Street");
        generator.writeStringField("city", "Beverly Hills");
        generator.writeStringField("state", "CA");
        generator.writeNumberField("zipcode", 90210);
        generator.writeEndObject();
        generator.writeFieldName("phoneNumbers");
        generator.writeStartArray();
        generator.writeStartObject();
        generator.writeStringField("type", "home");
        generator.writeStringField("number", "310 555-1234");
        generator.writeEndObject();
        generator.writeStartObject();
        generator.writeStringField("type", "fax");
        generator.writeStringField("number", "310 555-4567");
        generator.writeEndObject();
        generator.writeEndArray();
        generator.writeEndObject();

        generator.close();

        out.println("person.json successfully generated");
    }
}
