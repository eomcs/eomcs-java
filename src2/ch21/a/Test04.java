// 예외처리 - 애플리케이션 예외 처리 II
package ch21.a;

import java.util.ArrayList;

public class Test04 {

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("홍길동");
    list.add("임꺽정");
    list.add("유관순");
    
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    
    // 애플리케이션 예외 처리
    // => JVM이 종료되지 않게 하려면 애플리케이션 예외를 처리해야 한다.
    // => 문법
    //    try { 예외가 발생할 수 있는 코드 } catch (Exception e) { 예외 처리 코드 }
    //
    //
    try {
      // try 블록에 예외가 발생할 수 있는 코드를 둔다.
      // 만약 에외가 발생한다면 예외 정보가 들어 있는 객체를 catch 블록으로 던진다.
      System.out.println(list.get(3));
      
    } catch (Exception e) {
      // catch 블록에서는 try에서 던진 예외 객체를 파라미터로 받는다.
      // 애플리케이션 예외는 Exception 계열의 객체이기 때문에 
      // Exception 파라미터로 받는다.
      //
      // 애플리케이션 예외는 적절한 조치를 취한 후 계속 애플리케이션 코드를 실행해도 된다.
      System.out.println("인덱스가 무효합니다.");
    }
    
    // 위의 try ~ catch ~ 블록에서 예외를 처리했기 때문에 
    // 예외가 발생하더라도 JVM이 프로그램을 종료하지 않고 
    // 다음 코드를 계속 실행할 수 있다.
    System.out.println("프로그램 종료!");
    
    // "예외처리 문법의 존재 이유"
    // => 예외가 발생하더라도 시스템을 멈추지 않고 계속 실행하게 하는 기술이다.
    // 
    
  }
}








