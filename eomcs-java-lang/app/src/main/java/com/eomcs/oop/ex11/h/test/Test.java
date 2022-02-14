package com.eomcs.oop.ex11.h.test;

public class Test {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    list.add("안중근");
    list.add("윤봉길");
    list.add("김구");
    System.out.println(list.size());

    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ",");
    }
    System.out.println();
    System.out.println("----------------------------------------------------");

    list.remove(0); // 홍길동 
    list.remove(4); // 김구
    list.remove(2); // 안중근
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ",");
    }
    System.out.println();
    System.out.println("----------------------------------------------------");

    list.remove(0);
    list.remove(0);
    list.remove(0);
    list.add("박보검");
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ",");
    }
    System.out.println();
    System.out.println("----------------------------------------------------");
  }

}
