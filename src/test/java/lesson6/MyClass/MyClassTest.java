package lesson6.MyClass;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
class MyClassTest {

    private Set<String> inicFieldsToCleanup() {
        Set<String> fieldsToCleanup = new HashSet<>();
        fieldsToCleanup.add("age");
        fieldsToCleanup.add("name");
        fieldsToCleanup.add("weight");
        fieldsToCleanup.add("status");
        fieldsToCleanup.add("person");
        fieldsToCleanup.add("flag");
        return fieldsToCleanup;
    }

    private HashMap<String, Integer> inicHahMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("age", 1);
        hashMap.put("name", 2);
        hashMap.put("weight", 3);
        hashMap.put("status", 4);
        hashMap.put("person", 5);
        return hashMap;
    }

    @Test
    void cleanupMapDeleteKey() {
        Set<String> fieldsToCleanup = new HashSet<>();
        fieldsToCleanup.add("age");
        fieldsToCleanup.add("name");
        fieldsToCleanup.add("weight");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("status");
        fieldsToOutput.add("person");

        HashMap<String, Integer> hashMap = inicHahMap();

        HashMap<String, Integer> resultHashMap = new HashMap<>();
        resultHashMap.put("status", 4);
        resultHashMap.put("person", 5);

        MyClass.cleanup(hashMap, fieldsToCleanup, fieldsToOutput);
        Assert.assertEquals(hashMap, resultHashMap);
    }

    @Test
    public void cleanupMapException() {
        Set<String> fieldsToCleanup = new HashSet<>();
        fieldsToCleanup.add("age");
        fieldsToCleanup.add("name");
        fieldsToCleanup.add("weight");
        fieldsToCleanup.add("weightt");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("status");
        fieldsToOutput.add("person");

        ClassForTest testClass = new ClassForTest();

        Assert.assertThrows(IllegalArgumentException.class, () -> MyClass.cleanup(testClass, fieldsToCleanup, fieldsToOutput));
    }

    @Test
    public void cleanupObjectNullPrimitive() {
        Set<String> fieldsToCleanup = inicFieldsToCleanup();

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("person");

        ClassForTest testClass = new ClassForTest();

        MyClass.cleanup(testClass, fieldsToCleanup, fieldsToOutput);

        Assert.assertNull(testClass.name);
        Assert.assertEquals(0, Math.abs(testClass.weight - 0), 0.0);
        Assert.assertEquals(testClass.age, 0);
        Assert.assertEquals(testClass.status, Character.MIN_VALUE);
        Assert.assertFalse(testClass.flag);
    }

    @Test
    public void cleanupObjectNull() {
        Set<String> fieldsToCleanup = new HashSet<>();
        fieldsToCleanup.add("person");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("person");

        ClassForTest testClass = new ClassForTest();

        MyClass.cleanup(testClass, fieldsToCleanup, fieldsToOutput);

        Assert.assertNull(testClass.person);
    }
}