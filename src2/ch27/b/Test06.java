// reflection API - 메서드의 상세정보 꺼내기 II
package ch27.b;

import java.lang.reflect.Method;

public class Test06 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = C.class;
    
    // 상속 받은 메서드를 포함하여 모든 public 메서드의 정보 출력하기
    // 
    Method[] methods = clazz.getMethods();
    for (Method m : methods) {
      // 메서드가 실제 정의된 클래스의 이름 출력하기
      System.out.printf("클래스명: %s\n", 
          m.getDeclaringClass().getSimpleName());
      System.out.printf("  ==> %s\n", m.getName());
    }
    
    
  }

}





