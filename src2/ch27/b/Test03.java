// reflection API - getMethods()와 getDeclaredMethods()
package ch27.b;

import java.lang.reflect.Method;

public class Test03 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = C.class;
    
    // getMethods()
    // => 수퍼 클래스의 메서드도 리턴한다.
    // => public 메서드만 리턴한다.
    Method[] methods = clazz.getMethods();
    for (Method m : methods) {
      System.out.println(m.getName());
    }
    System.out.println("----------------------");
    
    // getDeclaredMethods()
    // => 현재 클래스의 메서드만 리턴한다.
    // => private,(default),protected,public 메서드 모두 리턴한다.
    methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println(m.getName());
    }
  }

}
