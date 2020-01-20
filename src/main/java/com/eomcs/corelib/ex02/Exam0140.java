// String - 다형적 변수와 형변환, toString()
package com.eomcs.corelib.ex02;

public class Exam0140 {
  public static void main(String[] args) {
    String s1 = new String("Hello");
    System.out.println(s1); // println(String) 호출
    System.out.println(s1.toString()); // println(String) 호출

    String s2 = s1.toString(); // s1이 String이기 때문에
    // toString()은 따로 String을 만들지 않고
    // 그냥 s1 주소를 리턴한다.
    System.out.println(s1 == s2); // true

  }
}


