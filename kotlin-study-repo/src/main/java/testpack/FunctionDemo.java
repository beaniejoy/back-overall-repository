package testpack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

    public static void readAndPrint(Function<String, Integer> func) {
        System.out.println(func.apply("25"));
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {

        System.out.println(Lion.class.getName());
        Class<?> clazz = Class.forName(Lion.class.getName());

        System.out.println(clazz);
        System.out.println("fields " + Arrays.toString(clazz.getFields()));
        for (Field field : clazz.getFields()) {
            System.out.println("field " + field.getType());
        }
    }
}
