package lesson2.MyHashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    private void fillHashMap(MyHashMap hashMap, int count)
    {
        for (int i = 0; i < count; i++) {
            hashMap.put(i,i);
        }
    }
    @Test
    void size() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>(25);
        fillHashMap(myTest,100);
        assertEquals(25,myTest.size());
    }

    @Test
    void isEmpty() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        assertEquals(true,myTest.isEmpty());
        fillHashMap(myTest,100);
        assertEquals(false,myTest.isEmpty());
    }

    @Test
    void containsKey() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        assertEquals(false,myTest.containsKey(111));
        assertEquals(true,myTest.containsKey(77));
    }

    @Test
    void containsValue() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        assertEquals(false,myTest.containsValue(111));
        assertEquals(true,myTest.containsValue(77));
    }

    @Test
    void get() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
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
        fillHashMap(myTest,100);
        assertEquals(55,myTest.get(55));
        myTest.remove(55);
        assertEquals(null,myTest.get(55));
    }

    @Test
    void putAll() {
        MyHashMap<String,String> myTest1 = new MyHashMap<String, String>();
        MyHashMap<String,String> myTest2 = new MyHashMap<String, String>();
        fillHashMap(myTest1,100);
        myTest2.putAll(myTest1);
        assertEquals(55,myTest1.get(55));
        assertEquals(55,myTest2.get(55));
        assertEquals(1,myTest2.get(1));
        assertEquals(2,myTest2.get(2));
        assertEquals(88,myTest2.get(88));
    }

    @Test
    void clear() {
    }

    @Test
    void keySet() {
    }

    @Test
    void values() {
    }

    @Test
    void entrySet() {
        /*MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        myTest.entrySet();*/
    }
}