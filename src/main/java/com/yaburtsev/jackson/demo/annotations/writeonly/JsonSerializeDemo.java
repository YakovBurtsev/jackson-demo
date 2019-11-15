package com.yaburtsev.jackson.demo.annotations.writeonly;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.File;
import java.io.IOException;

import static java.lang.System.out;

public class JsonSerializeDemo {

    public static void main(String[] args) throws Exception {
        String jsonContent =
            "{" +
                "   \"color\": \"black\"" +
                "}";

        Canvas canvas = new ObjectMapper()
            .readerFor(Canvas.class)
            .readValue(jsonContent);

        out.printf("Color = %s%n", canvas.color);

        new ObjectMapper().writeValue(new File("color.json"), canvas);
    }

    private enum Color {
        BLACK, UNKNOWN
    }

    private static class Canvas {

        @JsonDeserialize(using = ColorDeserializer.class)
        @JsonSerialize(using = ColorSerializer.class)
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

    private static class ColorSerializer extends JsonSerializer<Color> {

        @Override
        public void serialize(Color color,
                              JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            switch (color) {
                case BLACK:
                    jsonGenerator.writeString("black");
                    break;
                default:
                    jsonGenerator.writeString("unknown");
            }
        }
    }
}
