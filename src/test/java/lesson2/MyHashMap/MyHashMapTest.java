package lesson2.MyHashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private void fillHashMap(MyHashMap hashMap)
    {
        for (int i = 0; i < 100; i++) {
            hashMap.put(i,i);
        }
    }
    @Test
    void size() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>(25);
        fillHashMap(myTest);
        assertEquals(25,myTest.size());
    }

    @Test
    void isEmpty() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        assertEquals(true,myTest.isEmpty());
        fillHashMap(myTest);
        assertEquals(false,myTest.isEmpty());
    }

    @Test
    void containsKey() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest);
        assertEquals(false,myTest.containsKey(111));
        assertEquals(true,myTest.containsKey(77));
    }

    @Test
    void containsValue() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest);
        assertEquals(false,myTest.containsValue(111));
        assertEquals(true,myTest.containsValue(77));
    }

    @Test
    void get() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest);
        assertEquals(1,myTest.get(1));
        assertEquals(2,myTest.get(2));
        assertEquals(55,myTest.get(55));
        assertEquals(87,myTest.get(87));
    }

    @Test
    void put() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        assertEquals(null,myTest.get("11"));
        myTest.put("11","val");
        assertEquals("val",myTest.get("11"));
        myTest.put("11","val2");
        assertEquals("val2",myTest.get("11"));
    }

    @Test
    void remove() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest);
        assertEquals(55,myTest.get(55));
        myTest.remove(55);
        assertEquals(null,myTest.get(55));
    }
}