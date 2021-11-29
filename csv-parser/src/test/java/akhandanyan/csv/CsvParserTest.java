package akhandanyan.csv;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CsvParserTest {

    @Test
    public void givenFile_parseToObject_assertInstance() throws FileNotFoundException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test.csv");

        try {
            List<SimpleClass> instances = new CsvParser().parse(is, SimpleClass.class);
            assertEquals(2, instances.size());

            assertEquals(40, instances.get(1).age);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void givenClass_getAnnotatedFields_assertPresent() throws NoSuchFieldException {
        Map<String, Field> annotatedFields = CsvParser.getAnnotatedFields(SimpleClass.class);

        Field nameField = SimpleClass.class.getDeclaredField("name");
        assertEquals(annotatedFields.get("name"), nameField);
    }

    public static class SimpleClass {
        private String name;
        @CsvProperty(value = "columnName")
        private String column;
        private Integer age;
    }
}
