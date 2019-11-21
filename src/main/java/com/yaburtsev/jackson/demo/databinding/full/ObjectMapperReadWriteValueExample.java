package com.yaburtsev.jackson.demo.databinding.full;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaburtsev.jackson.demo.core.ObjectMapperReadTree;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.out;

/*
Full data binding converts to and from any Java bean type in addition to
Maps, Lists, Strings, Numbers, Booleans, and the null reference.

NOTE: A getter method makes a non-public field serializable and deserializable
 (once a field has a getter method, it’s considered to be a property).
  In contrast, a setter method makes a non-public field deserializable only.
   For example, in Person, commenting out setAge() only doesn’t affect age’s serializability/deserializability.
    However, commenting out getAge() only prevents age from being serialized
     (e.g., via one of ObjectMapper’s writeValue() methods).
*/
public class ObjectMapperReadWriteValueExample {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path = Paths.get(ObjectMapperReadTree.class.getResource("/person.json").toURI());

        ObjectMapper mapper = new ObjectMapper();

        //deserialization
        Person person = mapper.readValue(path.toFile(), Person.class);
        out.println(person);

        //serialization
        mapper.writeValue(out, person);
    }
}
