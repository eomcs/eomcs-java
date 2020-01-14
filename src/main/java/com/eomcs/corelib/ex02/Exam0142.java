// String - 다형적 변수와 형변환, toLowerCase()
package com.eomcs.corelib.ex02;

public class Exam0142 {
  public static void main(String[] args) {
    
    Object obj = new String("Hello");
    
    // obj를 통해 원래 인스턴스 메서드를 호출하고 싶다면 
    // 다음과 같이 원래 타입으로 형변환하라.
    String str = ((String)obj).toLowerCase();
    System.out.println(str);
    
    // 또는 다음과 같이 원래 타입의 레퍼런스에 저장한 다음 사용하라.
    String x1 = (String) obj;
    str = x1.toLowerCase();
    System.out.println(str);
  }
}





