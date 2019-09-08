package com.assignment.engine.ds;

public class DoublyLinkedList {

    /**
     * Doubly linked list node.
     */
    public static class Node {
        private int key;
        private String value;
        private Node prevNode;
        private Node nextNode;

        private Node(){

        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


    private Node headPointer, tailPointer;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public DoublyLinkedList() {

        // Initialize the dummy head of the cache
        headPointer = new Node();
        headPointer.prevNode = null;

        // Init the dummy tail of the cache
        tailPointer = new Node();
        tailPointer.nextNode = null;

        // Wire the head and tail together
        headPointer.nextNode = tailPointer;
        tailPointer.prevNode = headPointer;
    }



    /**
     * Insertions to the doubly linked list will always
     * be right after the dummy head.
     *
     * @param key: int
     * @param value: String
     * @return node instance
     */
    public Node addNode(int key, String value) {

        // Wire the node being inserted
        Node node = new Node();
        node.key = key;
        node.value = value;
        return addNode(node);
    }

    private Node addNode(Node node) {
        node.prevNode = headPointer;
        node.nextNode = headPointer.nextNode;

        // Re-wire the head's old next
        headPointer.nextNode.prevNode = node;

        // Re-wire the head to point to the inserted node
        headPointer.nextNode = node;

        size++;
        return node;
    }

    /**
     * Remove the given node from the doubly linked list
     *
     * @param node: Node
     */
    public void removeNode(Node node) {

        // Grab reference to the prev and next of the node
        Node savedPrev = node.prevNode;
        Node savedNext = node.nextNode;

        // Cut out going forwards
        savedPrev.nextNode = savedNext;

        // Cut out going backards
        savedNext.prevNode = savedPrev;
        --size;
    }

    /**
     * Move a node to the head of the doubly linked list
     *
     * @param node: Node
     */
    public void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Pop the last item from the structure
     *
     * @return Node instance
     */
    public Node popTail() {
        Node itemBeingRemoved = tailPointer.prevNode;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
    }
}
