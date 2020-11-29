package lesson2.MyHashMap;

import lesson2.Node.Node;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Entity;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyHashMapTest {

    private void fillHashMap(MyHashMap hashMap, int count)
    {
        for (int i = 0; i < count; i++) {
            hashMap.put(i,i);
        }
    }
    @Test
    void length() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>(25);
        fillHashMap(myTest,100);
        assertEquals(25,myTest.length());
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
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        assertEquals(67,myTest.get(67));
        assertEquals(78,myTest.get(78));
        assertEquals(1,myTest.get(1));
        myTest.clear();
        assertEquals(null,myTest.get(67));
        assertEquals(null,myTest.get(78));
        assertEquals(null,myTest.get(1));

    }

    @Test
    <K>
    void keySet() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        Set<K> keys = myTest.keySet();
        for (int i = 0; i < keys.size(); i++) {
            assertEquals(true, keys.contains(i));
        }
    }

    @Test
    void values() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        ArrayList<Node> array = (ArrayList<Node>) myTest.values();
        for (int i = 0; i < array.size(); i++) {
            Node node = array.get(i);
            assertEquals(true,myTest.containsKey(node.getKey()));
            assertEquals(true,myTest.containsValue(node.getValue()));
        }
    }

    @Test
    void entrySet() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        Set<Map.Entry<String, String>> hash = myTest.entrySet();
        for (Map.Entry<String,String> entry : hash)
        {
            assertEquals(true,myTest.containsKey(entry.getKey()));
            assertEquals(true,myTest.containsKey(entry.getValue()));
        }
    }

    @Test
    void size() {
        MyHashMap<String,String> myTest = new MyHashMap<String, String>();
        fillHashMap(myTest,100);
        assertEquals(100, myTest.size());
        myTest.remove(16);
        assertEquals(99, myTest.size());
        myTest.remove(4);
        myTest.remove(87);
        myTest.remove("66");
        myTest.remove(null);
        assertEquals(97, myTest.size());
    }
}