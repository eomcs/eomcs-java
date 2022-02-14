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

}
