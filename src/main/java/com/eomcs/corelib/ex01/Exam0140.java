// String - 다형적 변수와 형변환, toString()
package com.eomcs.corelib.ex01;

public class Exam0140 {
  public static void main(String[] args) {
    String s1 = new String("Hello");
    System.out.println(s1);
    System.out.println(s1.toString());

    String s2 = s1.toString(); // s1이 String이기 때문에 
    // toString()은 따로 String을 만들지 않고 
    // 그냥 s1 주소를 리턴한다.
    if (s1 == s2) { // 확인!
      System.out.println("s1 == s2");
    }

    Object obj = new String("Hello"); // 인스턴스 주소가 100이라 가정하자;

    String x1 = (String) obj; // x1 = 100
    String x2 = obj.toString(); // x2 = 100

    x1.toLowerCase();
    ((String) obj).toLowerCase();

    x2.toLowerCase();
    obj.toString().toLowerCase();

    if (x1 == x2) {
      System.out.println("x1 == x2");
    }


  }
}





