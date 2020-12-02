package lesson2.MyHashMap;

import lesson2.Node.Node;

import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    Node<K, V>[] nodes;
    int length = 16;

    public MyHashMap() {
        nodes = new Node[length];
    }

    public MyHashMap(int length) {
        this.length = length;
        nodes = new Node[length];
    }

    /**
     * Возвращает длину массива
     *
     * @return длина массива
     */
    public int length() {
        return length;
    }

    /**
     * Возвращает количество элементов
     *
     * @return количество элементов
     */
    @Override
    public int size() {
        int size = 0;
        for (Node<K, V> node : nodes) {
            while (node != null) {
                size++;
                node = node.getNext();
            }
        }
        return size;
    }

    /**
     * определяет пустой ли hashmap
     *
     * @return флаг пустой ли хэшмап
     */
    public boolean isEmpty() {
        for (Node<K, V> node : nodes) {
            if (node != null)
                return false;
        }
        return true;
    }

    /**
     * Определяет содержится ли в hashmap искомый ключ
     *
     * @param key искомый ключ
     * @return флаг содержится ли ключ
     */
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        if (nodes[index] == null)
            return false;
        Node<K, V> result = findNodeKey(nodes[index], key);
        return result != null;
    }

    /**
     * Определяет содержится ли в hashmap искомое значение
     *
     * @param value искомое значение
     * @return флаг содержится ли значение
     */
    public boolean containsValue(Object value) {
        for (Node<K, V> node : nodes) {
            if (node.getValue().equals(value))
                return true;
            else if (node.getNext() != null) {
                Node<K, V> findNode = findNodeValue(node, value);
                if (findNode == null)
                    continue;
                else
                    return true;
            }
        }
        return false;
    }

    /**
     * Возвращает значение по ключу
     *
     * @param key ключ
     * @return значение
     */
    public V get(Object key) {
        int index = getIndex(key);
        if (nodes[index] == null)
            return null;
        Node<K, V> result = findNodeKey(nodes[index], key);
        if (result != null)
            return result.getValue();
        else
            return null;
    }

    /**
     * Находит элемент в связном списке по ключу
     *
     * @param node начальный элемент связного списка
     * @param key  ключ элемента, которого нужно найти
     * @return искомый элемент
     */
    private Node<K, V> findNodeKey(Node<K, V> node, Object key) {
        if (key == null && node.getKey() == null || node.getKey().equals(key))
            return node;
        else if (node.getNext() != null)
            return findNodeKey(node.getNext(), key);
        else
            return null;
    }

    /**
     * Находит элемент в связном списке по значению
     *
     * @param node  начальный элемент связного списка
     * @param value значение по которму нужно найти элемент
     * @return искомый элемент
     */
    private Node<K, V> findNodeValue(Node<K, V> node, Object value) {
        if (value == null && node.getValue() == null || node.getValue().equals(value))
            return node;
        else if (node.getNext() != null)
            return findNodeValue(node.getNext(), value);
        else
            return null;
    }

    /**
     * Находит предшедствующий элемент
     *
     * @param node     начальный элемент связного списка
     * @param findNode искомый элемент
     * @return предшедствующий элемент искомого элемента
     */
    private Node<K, V> findNodeBefore(Node<K, V> node, Node<K, V> findNode) {
        if (node != null && node.getNext() != null) {
            if (node.getNext().equals(findNode)) {
                return node;
            } else
                return findNodeBefore(node.getNext(), findNode);
        } else
            return null;
    }

    /**
     * Добавление hashmap
     *
     * @param key   ключ
     * @param value значение
     * @return значение
     */
    public V put(K key, V value) {
        if (key != null)
            return addNode(key, value);
        else
            return addNodeForNull(value);
    }

    /**
     * Добавление элемента с не нулевым ключом
     *
     * @param key   ключ
     * @param value значение
     * @return значение
     */
    private V addNode(K key, V value) {
        int index = getIndex(key);
        Node<K, V> node = nodes[index];
        Node<K, V> newNode = new Node<>(key, value, getNewHash(key.hashCode()));
        if (node == null) {
            nodes[index] = newNode;
            return newNode.getValue();
        } else if (node.getKey() != null && node.getKey().equals(key)) {
            node.setValue(value);
            return node.getValue();
        } else {
            newNode.setNext(node);
            nodes[index] = newNode;
            return newNode.getValue();
        }
    }

    /**
     * Добавление элемента с нулевым ключом
     *
     * @param value значение
     * @return значение
     */
    private V addNodeForNull(V value) {
        Node<K, V> newNode = new Node<>(null, value);
        Node<K, V> node = nodes[0];
        if (node != null) {
            newNode.setNext(node);
            nodes[0] = newNode;
            return node.getValue();
        } else {
            nodes[0] = newNode;
            return newNode.getValue();
        }
    }

    /**
     * Удаление из hashmap по ключу
     *
     * @param key ключ
     * @return значение
     */
    public V remove(Object key) {
        int index = getIndex(key);
        Node<K, V> findNode = findNodeKey(nodes[index], key);
        if (findNode != null) {
            Node<K, V> beforeNode = findNodeBefore(nodes[index], findNode);
            if (beforeNode != null) {
                beforeNode.setNext(findNode.getNext());
                return findNode.getValue();
            } else if (findNode.getNext() != null) {
                nodes[index] = findNode.getNext();
            } else
                nodes[index] = null;
            return findNode.getValue();
        }
        return null;
    }

    public void putAll(Map m) {
        for (Entry<K, V> entry : ((Map<K, V>) m).entrySet()) {
            Node<K, V> node = (Node<K, V>) entry;
            put(node.getKey(), node.getValue());
        }
    }

    public void clear() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> node : nodes) {
            while (node != null) {
                keys.add(node.getKey());
                node = node.getNext();
            }
        }
        return keys;
    }

    public Collection<V> values() {
        ArrayList<V> array = new ArrayList<>();
        for (Node<K, V> node : nodes) {
            while (node != null) {
                array.add(node.getValue());
                node = node.getNext();
            }
        }
        return array;
    }

    public Set<Entry<K, V>> entrySet() {
        HashSet<Entry<K, V>> hash = new HashSet<>();
        for (Node<K, V> node : nodes) {
            while (node != null) {
                hash.add(node);
                node = node.getNext();
            }
        }
        return hash;
    }

    /**
     * Возвращает новый hash
     *
     * @param hashCode хэшкод
     * @return новый хэшкод
     */
    private int getNewHash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    /**
     * Возвращает индекс по ключу
     *
     * @param key ключ
     * @return индекс
     */
    private int getIndex(Object key) {
        int index = 0;
        if (key != null) {
            int hashCode = getNewHash(key.hashCode());
            index = hashCode & (length - 1);
        }
        return index;
    }

}