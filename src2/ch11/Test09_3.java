// Wrapper 클래스 : 사용 후
package ch11;

import java.util.Date;

public class Test09_3 {
  public static void main(String[] args) {
    // Primitive Type의 값을 전달할 때는 Test09_2의 경우와 같이 
    // 각 타입의 값을 받는 메서드를 여러 개 만들어야 하지만,
    // Wrapper 클래스의 인스턴스를 사용하면 타입의 종류와 상관없이 
    // 파라미터로 값을 받는 메서드를 한 개만 만들어도 된다.
    Integer obj1 = Integer.valueOf(100);
    Float obj2 = Float.valueOf(3.14f);
    Character obj3 = Character.valueOf('A');
    Boolean obj4 = Boolean.valueOf(true);
    
    printObject(obj1);
    printObject(obj2);
    printObject(obj3); 
    printObject(obj4);
  }
  
  static void printObject(Object obj) {
    System.out.println("값 => " + obj.toString());
  }

}













