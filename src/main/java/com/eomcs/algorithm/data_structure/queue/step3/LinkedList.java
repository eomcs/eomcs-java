// LinkedList : 목록으로 다루는 값을 특정 타입으로 제한하기 위해 제네릭(generic) 적용하기
package com.eomcs.algorithm.data_structure.queue.step3;

import java.lang.reflect.Array;

public class LinkedList<T> {
  Node<T> head;
  Node<T> tail;
  int size = 0;
  
  public LinkedList() {
  }
  
  public boolean add(T value) {
    Node<T> temp = new Node<>(value);
    if (head == null)
      head = temp;
    if (tail != null)
      tail.next = temp;
    tail = temp;
    size++;
    return true;
  }
  
  public T get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }
  
  // 특정 위치의 값을 바꾼다.
  public T set(int index, T value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node<T> node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    T oldVal = node.value; // 노드에 저장된 기존 값 백업
    node.value = value; // 해당 노드의 값을 파라미터에서 받은 값으로 변경
    return oldVal; // 변경 전 값을 리턴
  }
  
  // 특정의 위치의 값을 삭제한다.
  public T remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node<T> deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {
      Node<T> node = head;
      for (int i = 0; i < index - 1; i++) { 
        // 삭제하려는 노드의 이전 노드까지 간다.
        node = node.next; 
      }
      // 이전 노드가 가리키는 다음 노드를 다음, 다음 노드를 가리키게 한다.
      deletedNode = node.next; // 삭제될 노드를 임시 보관한다.
      node.next = deletedNode.next; // 삭제될 노드의 다음 노드를 가리킨다.
      
      if (deletedNode == tail) { // 삭제할 노드가 마지막 노드라면 
        tail = node; // tail 노드를 변경한다.
      }
    }
    
    T oldVal = deletedNode.value; // 삭제될 노드의 값을 임시 보관한다.
    deletedNode.value = null; // 삭제될 노드가 다른 객체를 참조하지 않도록 초기화시킨다.
    deletedNode.next = null; // 이런 식으로 개발자가 메모리 관리에 기여할 수 있다.
    
    size--;
    
    return oldVal;
  }
  
  public int size() {
    return size;
  }
  
  public void clear() {
    if (size == 0)
      return;
    
    // 노드를 따라 가면서 삭제하기
    while (head != null) {
      Node<T> deletedNode = head;
      head = head.next;
      deletedNode.value = null;
      deletedNode.next = null;
    }
    
    tail = null;
    size = 0;
  }
  
  public Object[] toArray() {
    // LinkedList에 있는 데이터를 저장할 배열을 준비한다.
    Object[] arr = new Object[size];
    
    // LinkedList의 head에서 tail까지 반복하면서 배열에 value를 복사한다.
    // 방법1:
    /*
    Node node = head;
    for (int i = 0; i < size; i++) {
      arr[i] = node.value;
      node = node.next;
    }
    */
    
    // 방법2;
    Node<T> node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    
    // 배열을 리턴한다.
    return arr;
  }
  
  @SuppressWarnings("unchecked")
  public T[] toArray(T[] a) {
    if (a.length < size) {
      // 파라미터로 넘겨 받은 배열의 크기가 저장된 데이터의 개수 보다 작다면 
      // 이 메서드에서 새 배열을 만든다.
      a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
    }
    
    Node<T> node = head;
    for (int i = 0; i < size; i++) {
      a[i] = node.value;
      node = node.next;
    }
    if (a.length > size)
      a[size] = null;
    return a;
  }
  
  // Node 객체에 보관하는 데이터의 클래스 이름을 "타입 파라미터" T 에 받는다.
  static class Node<T> {
    T value;
    Node<T> next;
    
    public Node() {
    }
     
    public Node(T value) {
      this.value = value;
    }
  }
}







