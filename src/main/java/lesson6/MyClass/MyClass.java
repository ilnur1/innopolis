package lesson6.MyClass;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class MyClass {

    /**
     * Обнуляет поля объекта. в случе если объект реализует Map то очищает ключи и выводит значения на экран
     *
     * @param object          объект, в которм будут искаться поля или ключи
     * @param fieldsToCleanup список для очиски ключей или обнуления значений полей
     * @param fieldsToOutput  спсиок для вывода на экран ключей или полей
     */
    public static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        fieldsToCleanup.stream().distinct().forEach(x -> hashMap.put(x + " clean", true));
        fieldsToOutput.stream().distinct().forEach(x -> hashMap.put(x + " out", false));
        try {
            if (object instanceof Map) {
                Map<?, ?> map = (Map<?, ?>) object;
                for (Map.Entry<String, Boolean> entry : hashMap.entrySet()) {
                    String key = entry.getKey().split(" ")[0];
                    if (!map.containsKey(key))
                        throw new IllegalArgumentException("Не найдено поле");
                    if (entry.getValue())
                        deleteKeysFromMap(key, map);
                    else
                        printValuesFromMap(key, map);
                }
            } else {
                Class<?> clazz = object.getClass();
                for (Map.Entry<String, Boolean> entry : hashMap.entrySet()) {
                    Field field = clazz.getDeclaredField(entry.getKey().split(" ")[0]);
                    field.setAccessible(true);
                    if (entry.getValue())
                        setToNullAllField(object, field);
                    else
                        outputSetPrint(object, field);
                }
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Не найдено поле");
        }
    }

    /**
     * Вывод на экран значений мапы
     *
     * @param objPrint значение для вывода на экран
     * @param map      мапа
     */
    private static void printValuesFromMap(String objPrint, Map<?, ?> map) {
        System.out.println(map.get(objPrint));
    }

    /**
     * Удаление ключей из мапы
     *
     * @param removeKey значение для удаления
     * @param map       мапа
     */
    private static void deleteKeysFromMap(String removeKey, Map<?, ?> map) {
        map.remove(removeKey);
    }

    /**
     * Проставляет значение null полю объекта, в случае примитивных типов - значение по умолчанию
     *
     * @param object объект, где находятся искомые поля
     * @param field  искомое поле
     */
    private static void setToNullAllField(Object object, Field field) {

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

    /**
     * Вывод на экран поля
     *
     * @param object объект, где находятся искомые поля
     * @param field  искомое поле
     */
    private static void outputSetPrint(Object object, Field field) {
        try {
            if (field == null)
                throw new IllegalAccessException();
            System.out.println(field.get(object));
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

