package lesson2.Node;

import java.util.Map;
import java.util.Objects;

public class Node<K, V> implements Map.Entry<K,V> {
    final K key;
    int hashCode;
    V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, int hashCode) {
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
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

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Node) {
            Node<K, V> node = (Node<K, V>) obj;
            return (Objects.equals(key, node.getKey()) &&
                    Objects.equals(value, node.getValue()));
        } else
            return false;
    }

    @Override
    public int hashCode() {
        hashCode ^= key.hashCode() + (hashCode >>> 20) ^ (hashCode >>> 12);
        hashCode = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        return hashCode;
    }
}
