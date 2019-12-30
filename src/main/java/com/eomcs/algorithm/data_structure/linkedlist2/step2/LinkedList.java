// LinkedList : Node 클래스를 중첩클래스(static nested class)로 만들기
package com.eomcs.algorithm.data_structure.linkedlist2.step2;

public class LinkedList {
  Node head;
  Node tail;
  int size = 0;
  
  public LinkedList() {
  }
  
  public boolean add(Object value) {
    Node temp = new Node(value);
    if (head == null)
      head = temp;
    if (tail != null)
      tail.next = temp;
    tail = temp;
    size++;
    return true;
  }
  
  public Object get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    return node.value;
  }
  
  // 특정 위치의 값을 바꾼다.
  public Object set(int index, Object value) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node node = head;
    for (int i = 0; i < index; i++) {
      node = node.next;
    }
    Object oldVal = node.value; // 노드에 저장된 기존 값 백업
    node.value = value; // 해당 노드의 값을 파라미터에서 받은 값으로 변경
    return oldVal; // 변경 전 값을 리턴
  }
  
  // 특정의 위치의 값을 삭제한다.
  public Object remove(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("인덱스가 유효하지 않습니다!");
    
    Node deletedNode = null;
    if (index == 0) {
      deletedNode = head;
      head = deletedNode.next;
    } else {
      Node node = head;
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
    
    Object oldVal = deletedNode.value; // 삭제될 노드의 값을 임시 보관한다.
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
      Node deletedNode = head;
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
    Node node = head;
    int i = 0;
    while (node != null) {
      arr[i++] = node.value;
      node = node.next;
    }
    
    // 배열을 리턴한다.
    return arr;
  }
  
  // LinkedList에서 사용하는 클래스라면 굳이 패키지 멤버 클래스로 만들 필요가 없다.
  // LinkedList 안에 선언하여 중첩 클래스로 정의하는 것이 
  // 소스 코드의 유지보수에 좋다. 
  // 외부에 직접 노출되지 않기 때문에 쓸데없는 클래스를 감추는 효과도 있다.
  static class Node {
    Object value;
    Node next;
    
    public Node() {
    }
    
    public Node(Object value) {
      this.value = value;
    }
  }
}







