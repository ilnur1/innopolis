package lesson2.MyHashMap;

import lesson2.Node.Node;

import java.util.*;

public class MyHashMap<K, V> implements Map {

    Node<K, V>[] nodes;
    int length = 16;

    public MyHashMap() {
        nodes = new Node[length];
    }

    public MyHashMap(int length) {
        this.length = length;
        nodes = new Node[length];
    }

    public int length() {
        return length;
    }

    @Override
    public int size() {
        int size=0;
        for (Node node : nodes)
        {
            while (node != null)
            {
                size++;
                node=node.getNext();
            }
        }
        return size;
    }

    /**
     * определяет пустой ли hashmap
     * @return
     */
    public boolean isEmpty() {
        for(Node node : nodes){
            if(node != null)
                return false;
        }
        return true;
    }

    /**
     * Определяет содержится ли в hashmap искомый ключ
     * @param key искомый ключ
     * @return
     */
    public boolean containsKey(Object key) {
        int index = getIndex(key);
        if(nodes[index] == null)
            return false;
        Node result = findNodeKey(nodes[index], key);
        if (result != null)
            return true;
        else
            return false;
    }

    /**
     * Определяет содержится ли в hashmap искомое значение
     * @param value искомое значение
     * @return
     */
    public boolean containsValue(Object value) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].getValue().equals(value))
                return true;
            else if(nodes[i].getNext() != null){
                Node findNode = findNodeValue(nodes[i],value);
                if(findNode == null)
                    continue;
                else
                    return true;
            }
        }
        return false;
    }

    /**
     * Возвращает значение по ключу
     * @param key
     * @return
     */
    public V get(Object key)  {
        int index = getIndex(key);
        if(nodes[index] == null)
            return null;
        Node result = findNodeKey(nodes[index], key);
        if (result != null)
            return (V) result.getValue();
        else
            return null;
    }

    /**
     * Находит элемент в связном списке по ключу
     * @param node
     * @param key
     * @return искомый элемент
     */
    private Node findNodeKey(Node node, Object key) {
        if (node.getKey().equals(key))
            return node;
        else if (node.getNext() != null)
            return findNodeKey(node.getNext(), key);
        else
            return null;
    }

    /**
     * Находит элемент в связном списке по значению
     * @param node
     * @param value
     * @return искомый элемент
     */
    private Node findNodeValue(Node node, Object value) {
        if (node.getValue().equals(value))
            return node;
        else if (node.getNext() != null)
            return findNodeValue(node.getNext(), value);
        else
            return null;
    }

    /**
     * Находит предшедствующий элемент
     * @param node начальный элемент связного списка
     * @param findNode искомый элемент
     * @return предшедствующий элемент искомого элемента
     */
    private Node findNodeBefore(Node node, Node findNode) {
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
     * @param key
     * @param value
     * @return
     */
    public Object put(Object key, Object value) {
        if (key != null)
            return addNode(key, value);
        else
            return addNodeForNull(value);
    }

    /**
     * Добавление элемента с не нулевым ключом
     * @param key
     * @param value
     * @return
     */
    private Object addNode(Object key, Object value) {
        int index = getIndex(key);
        Node node = nodes[index];
        Node newNode = new Node(key, value,getNewHash(key.hashCode()));
        if(node == null)
        {
            nodes[index] = newNode;
            return newNode;
        }else if (node.getKey().equals(key)) {
            node.setValue(value);
            return node;
        } else {
            newNode.setNext(node);
            nodes[index] = newNode;
            return newNode;
        }
    }

    /**
     * Добавление элемента с нулевым ключом
     * @param value
     * @return
     */
    private Object addNodeForNull(Object value) {
        Node newNode = new Node(null, value);
        Node node = nodes[0];
        if (node != null) {
            newNode.setNext(node);
            nodes[0] = newNode;
            return node;
        } else {
            nodes[0] = newNode;
            return newNode;
        }
    }

    /**
     * Удаление из hashmap по ключу
     * @param key
     * @return
     */
    public V remove(Object key) {
        int index = getIndex(key);
        Node findNode = findNodeKey(nodes[index], key);
        if (findNode != null) {
            Node beforeNode = findNodeBefore(nodes[index], findNode);
            if (beforeNode != null) {
                beforeNode.setNext(findNode.getNext());
                return (V) findNode.getValue();
            } else if(findNode.getNext() != null) {
                nodes[index] = findNode.getNext();
            } else
                nodes[index] = null;
            return (V) findNode.getValue();
        }
        return null;
    }

    public void putAll(Map m) {
        Map<K,V> map = m;
        for (Entry<K, V> entry: map.entrySet()) {
            Node node = (Node) entry;
            put(node.getKey(),node.getValue());
        }
    }

    public void clear() {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = null;
        }
    }

    public Set keySet() {
        Set<K> keys = new HashSet<>();
        for (Node node : nodes) {
            while (node !=null) {
                keys.add((K) node.getKey());
                node = node.getNext();
            }
        }
        return keys;
    }

    public Collection values() {
        ArrayList<Node> array = new ArrayList<>();
        for (Node node : nodes) {
            while (node !=null) {
                array.add(node);
                node = node.getNext();
            }
        }
        return array;
    }

    public Set<Entry<K,V>> entrySet() {
        HashSet<Entry<K,V>> hash = new HashSet<>();
        for (Node<K,V> node : nodes){
            while (node != null)
            {
                hash.add(node);
                node = node.getNext();
            }
        }
        return hash;
    }

    /**
     * Возвращает новый hash
     * @param hashCode
     * @return
     */
    private int getNewHash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    /**
     * Возвращает индекс по ключу
     * @param key
     * @return
     */
    private int getIndex(Object key)
    {
        int index = 0;
        if(key != null) {
            int hashCode = getNewHash(key.hashCode());
            index = hashCode & (length - 1);
        }
        return index;
    }

}