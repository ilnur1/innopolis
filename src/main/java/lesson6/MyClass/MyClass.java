package lesson6.MyClass;

import java.lang.reflect.Field;
import java.util.*;

public class MyClass {

    public static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        Class<?> clazz = object.getClass();

        if (!(object instanceof Map)) {
            chekSetFieldsNames(fieldsToCleanup, fieldsToOutput, clazz);

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);

                setToNullAllField(object, fieldsToCleanup, field);

                outputSetPrint(object, fieldsToOutput, field);
            }
        } else {
            Map<?, ?> map = (Map<?, ?>) object;
            for (String s : fieldsToOutput) {
                if (!map.containsKey(s)) {
                    throw new IllegalArgumentException("Поле не найдено");
                }
            }
            deleteKeysFromMap(fieldsToCleanup, map);
            printValuesFromMap(fieldsToOutput, map);
        }
    }

    /**
     * Проверка содержания полей. в случае не нахождения выкидывает ошибку
     * @param fieldsToCleanup список для очиски ключей или обнуления значений полей
     * @param fieldsToOutput спсиок для вывода на экран ключей и полей
     * @param clazz объект, в которм должны хранятся ключи или поля
     */
    private static void chekSetFieldsNames(Set<String> fieldsToCleanup, Set<String> fieldsToOutput, Class<?> clazz) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        Set<String> fieldNames = new HashSet<>();

        fields.forEach(v -> fieldNames.add(v.getName()));

        if (!fieldNames.containsAll(fieldsToCleanup) || !fieldNames.containsAll(fieldsToOutput)) {
            throw new IllegalArgumentException("Не найдено поле");
        }
    }

    /**
     * Вывод на экран значений мапы
     * @param fieldsToOutput список ключей
     * @param map мапа
     */
    private static void printValuesFromMap(Set<String> fieldsToOutput, Map<?, ?> map) {
        fieldsToOutput.forEach(v -> {
            if (map.get(v) != null) {
                System.out.println(map.get(v));
            }
        });

    }

    /**
     * Удаление ключей из мапы
     * @param fieldsToCleanup список ключей
     * @param map мапа
     */
    private static void deleteKeysFromMap(Set<String> fieldsToCleanup, Map<?, ?> map) {
        fieldsToCleanup.forEach(map::remove);
    }

    /**
     * Проставляет значение null полю объекта, в случае примитивных типов - значение по умолчанию
     * @param object объект, где находятся искомые поля
     * @param fieldsToCleanup список полей, в котором будет происходить поиск
     * @param field искомое поле
     */
    private static void setToNullAllField(Object object, Set<String> fieldsToCleanup, Field field) {
        for (String nameField : fieldsToCleanup) {
            if (field.getName().equals(nameField)) {
                try {
                    if (field.getType().isPrimitive()) {
                        switch (field.getType().toString()) {
                            case "String":
                                field.set(object, "");
                                break;
                            case "char":
                                field.set(object, Character.MIN_VALUE);
                                break;
                            case "boolean":
                                field.set(object, false);
                                break;
                            default:
                                field.set(object, 0);
                        }
                    } else {
                        field.set(object, null);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Вывод на экран поля
     * @param object объект, где находятся искомые поля
     * @param fieldsToOutput список полей, в котором будет происходить поиск
     * @param field искомое поле
     */
    private static void outputSetPrint(Object object, Set<String> fieldsToOutput, Field field) {
        try {
            for (String nameField : fieldsToOutput) {
                if (field.getName().equals(nameField)) {
                    System.out.println(field.get(object));
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class ClassForTest {
    final Person person = new Person("Петя", 30);
    public double weight = 77;
    protected String name = "Алеша";
    int age = 25;
    char status = 'e';
    boolean flag = true;
}

class Person {
    private String name = "Петя";
    private int weight = 50;

    public Person(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}

