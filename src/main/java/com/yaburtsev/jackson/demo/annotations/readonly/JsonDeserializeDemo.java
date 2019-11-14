package com.yaburtsev.jackson.demo.annotations.readonly;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

public class JsonDeserializeDemo {

    public static void main(String[] args) throws IOException {

        String jsonContent =
            "{" +
                "   \"color\": \"black\"" +
                "}";

        Canvas canvas = new ObjectMapper()
            .readerFor(Canvas.class)
            .readValue(jsonContent);

        System.out.printf("Color = %s%n", canvas.color);
    }

    private enum Color {
        BLACK,
        UNKNOWN
    }

    private static class Canvas {

        @JsonDeserialize(using = ColorDeserializer.class)
        public Color color;
    }

    private static class ColorDeserializer extends JsonDeserializer<Color> {

        @Override
        public Color deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext) throws IOException {
            switch (jsonParser.getText().toLowerCase()) {
                case "black":
                    return Color.BLACK;
                default:
                    return Color.UNKNOWN;
            }
        }
    }
}
