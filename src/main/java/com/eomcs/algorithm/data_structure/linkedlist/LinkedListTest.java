package com.eomcs.algorithm.data_structure.linkedlist;

public class LinkedListTest {

  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    list.add("ggg");
    
    System.out.println(list.set(0, "xxx"));
    System.out.println();
    
    print(list);
    
  }
  
  static void print(LinkedList<String> list) {
    //String[] arr = list.toArray(new String[0]);
    
    String[] arr = new String[list.size()];
    list.toArray(arr);
    
    for (String value : arr) {
      System.out.println(value);
    }
  }

}
