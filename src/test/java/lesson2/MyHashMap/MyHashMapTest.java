package lesson2.MyHashMap;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyHashMapTest {

    private void fillHashMap(Map<Integer, Integer> hashMap, int count) {
        for (int i = 0; i < count; i++) {
            hashMap.put(i, i);
        }
    }

    @Test
    void length() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>(25);
        fillHashMap(myTest, 100);
        assertEquals(25, myTest.length());
    }

    @Test
    void isEmpty() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashCode = new HashMap<>();
        assertEquals(true, hashCode.isEmpty());
        assertEquals(true, myTest.isEmpty());
        fillHashMap(myTest, 100);
        fillHashMap(hashCode, 100);
        assertEquals(false, myTest.isEmpty());
        assertEquals(false, hashCode.isEmpty());
    }

    @Test
    void containsKey() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashCode = new HashMap<>();
        fillHashMap(hashCode, 100);
        fillHashMap(myTest, 100);
        assertEquals(false, myTest.containsKey(111));
        assertEquals(false, hashCode.containsKey(111));
        assertEquals(true, myTest.containsKey(77));
        assertEquals(true, hashCode.containsKey(77));
    }

    @Test
    void containsValue() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashCode = new HashMap<>();
        fillHashMap(myTest, 100);
        fillHashMap(hashCode, 100);
        assertEquals(false, myTest.containsValue(111));
        assertEquals(false, hashCode.containsValue(111));
        assertEquals(true, myTest.containsValue(77));
        assertEquals(true, hashCode.containsValue(77));
    }

    @Test
    void get() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashCode = new HashMap<>();
        fillHashMap(myTest, 100);
        fillHashMap(hashCode, 100);
        assertEquals(1, myTest.get(1));
        assertEquals(1, hashCode.get(1));
        assertEquals(2, myTest.get(2));
        assertEquals(2, hashCode.get(2));
        assertEquals(55, myTest.get(55));
        assertEquals(55, hashCode.get(55));
        assertEquals(87, myTest.get(87));
        assertEquals(87, hashCode.get(87));
    }

    @Test
    void put() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashCode = new HashMap<>();
        myTest.put(1, 1);
        hashCode.put(1, 1);
        assertEquals(1, myTest.get(1));
        assertEquals(1, hashCode.get(1));
        myTest.put(1, 2);
        hashCode.put(1, 2);
        assertEquals(2, myTest.get(1));
        assertEquals(2, hashCode.get(1));
        myTest.put(1, 1);
        hashCode.put(1, 1);
        assertEquals(1, myTest.get(1));
        assertEquals(1, hashCode.get(1));

        myTest.put(null, 1);
        hashCode.put(null, 1);
        assertEquals(1, myTest.get(null));
        assertEquals(1, hashCode.get(null));
    }

    @Test
    void remove() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(myTest, 100);
        fillHashMap(hashMap, 100);
        assertEquals(55, myTest.get(55));
        assertEquals(55, hashMap.get(55));
        myTest.remove(55);
        hashMap.remove(55);
        assertEquals(null, myTest.get(55));
        assertEquals(null, hashMap.get(55));
    }

    @Test
    void putAll() {
        MyHashMap<Integer, Integer> myTest1 = new MyHashMap<>();
        MyHashMap<Integer, Integer> myTest2 = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(myTest1, 100);
        myTest2.putAll(myTest1);
        hashMap.putAll(myTest1);
        assertEquals(55, myTest1.get(55));
        assertEquals(55, myTest2.get(55));
        assertEquals(55, hashMap.get(55));
        assertEquals(1, myTest2.get(1));
        assertEquals(1, hashMap.get(1));
        assertEquals(2, myTest2.get(2));
        assertEquals(2, hashMap.get(2));
        assertEquals(88, myTest2.get(88));
        assertEquals(88, hashMap.get(88));
    }

    @Test
    void clear() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(hashMap, 100);
        fillHashMap(myTest, 100);
        assertEquals(67, myTest.get(67));
        assertEquals(78, myTest.get(78));
        assertEquals(1, myTest.get(1));
        assertEquals(67, hashMap.get(67));
        assertEquals(78, hashMap.get(78));
        assertEquals(1, hashMap.get(1));
        myTest.clear();
        hashMap.clear();
        assertEquals(null, myTest.get(67));
        assertEquals(null, myTest.get(78));
        assertEquals(null, myTest.get(1));
        assertEquals(null, hashMap.get(67));
        assertEquals(null, hashMap.get(78));
        assertEquals(null, hashMap.get(1));

    }

    @Test
    <K>
    void keySet() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(hashMap, 100);
        fillHashMap(myTest, 100);
        Set<K> keysHP = (Set<K>) hashMap.keySet();
        Set<K> keys = (Set<K>) myTest.keySet();
        for (int i = 0; i < keys.size(); i++) {
            assertEquals(true, keys.contains(i));
        }
        for (int i = 0; i < keysHP.size(); i++) {
            assertEquals(true, keysHP.contains(i));
        }
    }

    @Test
    <V>
    void values() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(hashMap, 100);
        fillHashMap(myTest, 100);
        Collection array = myTest.values();
        Collection arrayHP = hashMap.values();
        for (int i = 0; i < array.size(); i++)
            assertEquals(true, array.contains(i));
        for (int i = 0; i < arrayHP.size(); i++)
            assertEquals(true, arrayHP.contains(i));
    }

    @Test
    void entrySet() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(hashMap, 100);
        fillHashMap(myTest, 100);
        Set<Map.Entry<Integer, Integer>> hash = myTest.entrySet();
        Set<Map.Entry<Integer, Integer>> hashHP = hashMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : hash) {
            assertEquals(true, myTest.containsKey(entry.getKey()));
            assertEquals(true, myTest.containsKey(entry.getValue()));
        }
        for (Map.Entry<Integer, Integer> entry : hashHP) {
            assertEquals(true, hashMap.containsKey(entry.getKey()));
            assertEquals(true, hashMap.containsKey(entry.getValue()));
        }
    }

    @Test
    void size() {
        MyHashMap<Integer, Integer> myTest = new MyHashMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        fillHashMap(hashMap, 100);
        fillHashMap(myTest, 100);
        assertEquals(100, myTest.size());
        assertEquals(100, hashMap.size());
        myTest.remove(16);
        hashMap.remove(16);
        assertEquals(99, myTest.size());
        assertEquals(99, hashMap.size());
        myTest.remove(4);
        myTest.remove(87);
        myTest.remove("66");
        myTest.remove(null);
        hashMap.remove(4);
        hashMap.remove(87);
        hashMap.remove("66");
        hashMap.remove(null);
        assertEquals(97, myTest.size());
        assertEquals(97, hashMap.size());
    }
}