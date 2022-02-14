package com.eomcs.oop.ex11.h.test;

public class Node {
  Node prev;
  Object value;
  Node next;

  public Node() {}

  public Node(Object value) {
    this.value = value;
  }

  public Node(Object value, Node prev, Node next) {
    this.value = value;
    this.prev = prev;
    this.next = next;
  }
}
