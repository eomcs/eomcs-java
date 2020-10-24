// Stack 구현과 사용
package com.eomcs.basic.ex05;

import java.util.Stack;

public class Exam0110 {

  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("ddd");
    String s5 = new String("eee");

    Stack stack = new Stack();
    stack.push(s1); // aaa
    stack.push(s2); // aaa, bbb
    stack.push(s3); // aaa, bbb, ccc
    print(stack);

    System.out.println("==>" + stack.pop()); // ccc
    System.out.println("==>" + stack.pop()); // bbb
    print(stack); // aaa

    stack.push(s4); // aaa, ddd
    stack.push(s5); // aaa, ddd, eee
    print(stack);

    System.out.println("-----------------");

    String value;
    try {
      while (true) {
        System.out.println(stack.pop());
      }
    } catch (Exception e) {
      System.out.println("스택에서 더이상 꺼낼 데이터가 없습니다.");
    }
  }

  static void print(Stack list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + ", ");
    }
    System.out.println();
  }
}





















