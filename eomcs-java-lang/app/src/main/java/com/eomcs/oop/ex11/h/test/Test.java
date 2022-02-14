package com.eomcs.oop.ex11.h.test;

public class Test {

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    list.add("안중근");
    list.add("윤봉길");
    //    list.add("김구");
    System.out.println(list.size());

    System.out.println(list.get(2));
  }

}
