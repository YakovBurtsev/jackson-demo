package com.yaburtsev.jackson.demo;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.net.URL;

public class JsonParserExample {

    public static void main(String[] args) throws Exception {

        URL url = JsonParserExample.class.getResource("/person.json");

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(url);

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            if (jsonToken == null)
                break;
            System.out.printf("jsonToken = %s [%s] [%b] [%s]%n",
                jsonToken,
                jsonToken.asString(),
                jsonToken.isNumeric(),
                parser.getValueAsString());
        }
    }
}
