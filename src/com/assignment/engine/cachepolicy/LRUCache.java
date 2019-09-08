package com.assignment.engine.cachepolicy;

import com.assignment.engine.ds.DoublyLinkedList;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache class with Least Recently Used Cache Policy.
 */
public class LRUCache {

    private final static Logger LOGGER = Logger.getLogger(LRUCache.class.getName());

    private Map<Integer, DoublyLinkedList.Node> hashtable = new HashMap<>();
    private DoublyLinkedList linkedList = new DoublyLinkedList();
    private int maxCapacity;

    /**
     * LRUCache constructor to create cache
     * based on given capacity.
     *
     * @param maxCapacity: int
     */
    public LRUCache(int maxCapacity) {

        // capacity is set by config
        this.maxCapacity = maxCapacity;
        LOGGER.log(Level.DEBUG, "LRU cache created with capacity: " + maxCapacity);
    }

    /**
     * Retrieve an item from the cache.
     *
     * @param key: int
     * @return String, value or data
     */
    public String getData(int key) {

        DoublyLinkedList.Node node = hashtable.get(key);
        boolean itemFoundInCache = node != null;

        // Empty state check. Should raise exception here.
        if (!itemFoundInCache) {
            LOGGER.log(Level.DEBUG, "Key/Data not found in LRU cache");
            return null;
        }

        // Item has been accessed. Move to the front of the list.
        linkedList.moveToHead(node);
        LOGGER.log(Level.DEBUG, "Key/Data found in LRU cache. Moved to the front of the list.");

        return node.getValue();
    }

    /**
     * Add an item to the cache if it is not already there,
     * if it is already there, move it to the front of the cache
     *
     * @param key:   int
     * @param value: String
     */
    public void putData(int key, String value) {

        DoublyLinkedList.Node node = hashtable.get(key);
        boolean itemFoundInCache = node != null;

        if (!itemFoundInCache) {

            // Create a new node
            DoublyLinkedList.Node newNode = linkedList.addNode(key, value);
            ;

            // Add to the hashtable and the doubly linked list
            hashtable.put(key, newNode);

            // If over capacity evict an item with LRU cache eviction policy
            if (linkedList.getSize() > maxCapacity) {
                removeLeastUsedEntry();
            }

            LOGGER.log(Level.DEBUG,"Key/Data added in LRU cache.");

        } else {
            // and move it to the head
            linkedList.moveToHead(node);
            LOGGER.log(Level.DEBUG,"Key/Data moved to the head.");
        }

    }

    /**
     * Remove the least used entry from the doubly linked
     * list as well as the hashtable. Hence it is evicted
     * from the whole LRUCache structure
     */
    private void removeLeastUsedEntry() {
        DoublyLinkedList.Node tail = linkedList.popTail();
        hashtable.remove(tail.getKey());
        LOGGER.log(Level.DEBUG, "Removed least used Key/Data from LRU cache.");
    }


    /**
     * Remove the updated entry from the doubly linked list
     * as well as the hashtable.
     *
     * @param userId: int
     */
    public void removeUpdatedData(int userId) {
        DoublyLinkedList.Node node = hashtable.remove(userId);
        linkedList.removeNode(node);
        LOGGER.log(Level.DEBUG, "Removed updated Key/Data from LRU cache.");
    }
}

