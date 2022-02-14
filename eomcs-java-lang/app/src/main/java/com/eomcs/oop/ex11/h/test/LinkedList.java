package com.eomcs.oop.ex11.h.test;

public class LinkedList {
  Node head;
  Node tail;
  int size;

  public void add(Object value) {
    Node node = new Node(value);

    if (head == null) {
      tail = head = node;
    } else {
      node.prev = tail;
      tail.next = node;
      tail = node;
    }

    size++;
  }

  public int size() {
    return size;
  }

  public Object get(int index) {
    Node node = getNode(index);
    return node.value;
  }

  public void remove(int index) {
    Node node = getNode(index);

    if (size == 1) {
      head = tail = null;
    } else if (node == head) {
      head = node.next;
    } else if (node == tail) {
      tail = node.prev;
    } else {
      node.prev.next = node.next;
      node.next.prev = node.prev;
    }

    // 삭제된 노드는 다른 노드를 참조하지 않도록 초기화시킨다.
    // => 삭제된 노드끼리 참조함으로써 가비지가 되지 않는 문제를 방지함.
    node.prev = null;
    node.next = null;
    size--;
  }

  public Node getNode(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    Node node = head;
    int count = 0;
    while (count < index) {
      node = node.next;
      count++;
    }

    return node;
  }
}








