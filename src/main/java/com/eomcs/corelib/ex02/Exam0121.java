// StringBuffer - 문자열 비교
package com.eomcs.corelib.ex02;

public class Exam0121 {
  public static void main(String[] args) {
    StringBuffer b1 = new StringBuffer("Hello");
    StringBuffer b2 = new StringBuffer("Hello");

    System.out.println(b1 == b2); // 결과: false
    // => b1, b2 인스턴스가 다르니까 당연히 결과는 false이다.

    System.out.println(b1.equals(b2)); // 결과: false
    // => StringBuffer는 Object에서 상속 받은 equals()를 오버라이딩 하지 않았다.
    // => 따라서 원래 Object의 equals()를 사용한다.
    // => 원래 Object의 equals()는 인스턴스가 같은지를 비교한다.
    // 그러니 결과는 false이다.
    //

    // 해결책?
    // => StringBuffer에서 String을 꺼내 비교하라!
    /*
    String s1 = b1.toString();
    String s2 = b2.toString();
    System.out.println(s1.equals(s2));
    */
    System.out.println(b1.toString().equals(b2.toString()));
  }
}


