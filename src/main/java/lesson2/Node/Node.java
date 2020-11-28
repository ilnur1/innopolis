package lesson2.Node;

import java.util.Map;
import java.util.Objects;

public class Node<K,V> implements Map.Entry {
    int hashCode;
    final K key;
    V value;
    Node next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode  = hashCode;
    }

    public int getHashCode() {
        return hashCode();
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(Object value) {
        this.value = (V) value;
        return (V) value;
    }

    @Override
    public K getKey() {
        return key;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getIndex(int hashCode, int size){
        return  hashCode & (size - 1);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return  true;
        if(obj instanceof Node)
        {
            Node node = (Node)obj;
            return  (Objects.equals(key, node.getKey()) &&
                    Objects.equals(value, node.getValue()) //||
                    /*Objects.equals(hashCode, node.getHashCode())*/);
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        hashCode ^= key.hashCode() + (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode;
    }
}
