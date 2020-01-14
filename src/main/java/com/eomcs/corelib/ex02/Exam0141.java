// String - 다형적 변수와 형변환, toString()
package com.eomcs.corelib.ex02;

public class Exam0141 {
  public static void main(String[] args) {
    
    Object obj = new String("Hello"); // 인스턴스 주소가 100이라 가정하자;

    String x1 = (String) obj; // x1 = 100
    String x2 = obj.toString(); // x2 = 100
    //=> 레퍼런스를 통해 메서드를 호출할 때 
    //   항상 원래 객체의 클래스부터 메서드를 찾아 올라간다.
    //=> 따라서 obj가 가리키는 객체의 클래스가 String이기 때문에 
    //   obj.toString()은 String 클래스부터 해당 메서드를 찾는다.
    //=> String 클래스는 toString()을 오버라이딩 했기 때문에
    //   결국 obj.toString()은 String 클래스에서 오버라이딩 한 toString()을 호출하는 것이다.
    
    
    System.out.println(x1 == x2);
  }
}





