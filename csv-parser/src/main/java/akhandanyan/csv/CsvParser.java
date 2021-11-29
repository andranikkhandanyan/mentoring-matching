package akhandanyan.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class CsvParser {
    public static Options DEFAULT_OPTIONS = new Options()
            .setDelimiter(Options.DELIMITER_COMMA)
            .setFirstRowHeader(true);

    private final Options options;

    public CsvParser() {
        this(DEFAULT_OPTIONS);
    }

    public CsvParser(Options options) {
        this.options = options;
    }

    public <T> List<T> parse(InputStream is, Class<T> clazz) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String[] columnNames = options.getColumns();
        if (options.isFirstRowHeader()) {
            String header = br.readLine();
            columnNames = header.split(options.getDelimiter());
        }

        return new ArrayList<>(parseValues(br, clazz, columnNames, options.getDelimiter()));
    }

    private <T> List<T> parseValues(BufferedReader br, Class<T> clazz, String[] columnNames,
                                    String delimiter) {
        return br.lines().map(line -> {
            try {
                return mapToItem(line, clazz, columnNames, delimiter);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Failed to instantiate." +
                        "Please check empty constructor in a class", e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("No such field", e);
            } catch (ClassCastException e) {
                throw new RuntimeException("Wrong field type", e);
            }
        }).collect(Collectors.toList());
    }

    private static <T> T mapToItem(String line, Class<T> clazz, String[] columnNames, String delimiter)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassCastException {
        String[] values = line.split(delimiter);

        T instance = clazz.getConstructor().newInstance();

        Map<String, Field> annotatedFields = getAnnotatedFields(clazz);

        for (int i = 0; i < columnNames.length; i++) {
            String column = columnNames[i];
            Field field = annotatedFields.get(column);
            if (field != null) {
                setFieldValue(instance, field, values[i]);
            }
        }

        return instance;
    }

    /**
     * Set value to a <code>field</code> the on a given <code>instance</code>
     */
    private static void setFieldValue(Object instance, Field field, String value) {
        field.setAccessible(true);
        try {
            if (field.getType().isAssignableFrom(Integer.class)) {
                field.set(instance, Integer.parseInt(value));
            } else if (field.getType().isAssignableFrom(String.class)) {
                field.set(instance, value);
            } else if (field.getType().isAssignableFrom(Float.class)) {
                field.set(instance, Float.parseFloat(value));
            } else if (field.getType().isAssignableFrom(Double.class)) {
                field.set(instance, Double.parseDouble(value));
            } else if (field.getType().isAssignableFrom(Boolean.class)) {
                field.set(instance, Boolean.parseBoolean(value));
            } else if (field.getType().isEnum()) {
                field.set(instance, Enum.valueOf((Class<? extends Enum>) field.getType(), value));
            } else {
                throw new ClassCastException("Unknown type");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iterates over all of the fields of the given class, returns mapping of field name(annotated name
     * if annotated) and the fields itself
     */
    static Map<String, Field> getAnnotatedFields(Class<?> clazz) {
        Map<String, Field> annotatedFields = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(CsvProperty.class)) {
                annotatedFields.put(field.getAnnotation(CsvProperty.class).value(), field);
            } else {
                annotatedFields.put(field.getName(), field);
            }
        }

        return annotatedFields;
    }
}
