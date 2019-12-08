// reflection API - 특정 메서드만 조회하기
package ch27.b;

import java.lang.reflect.Method;

public class Test04 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = C.class;
    
    // 메서드명과 파라미터 타입으로 메서드 정보를 알아낼 수 있다.
    // 주의!
    // => primitive type 정보를 꺼낼 때는 타입명.class 를 사용하면 된다.
    //    즉 primitive type이 클래스가 아니지만 타입 정보를 꺼낼 때 class 변수를 사용할 수 있다.
    Method m = clazz.getMethod("plus", int.class, int.class);
    System.out.println(m.getName());
    
    // 메서드에 파라미터가 없을 경우 파라미터 타입을 지정하지 않으면 된다.
    m = clazz.getMethod("c_public");
    System.out.println(m.getName());
    
    // 메서드를 찾지 못하면 예외를 띄운다.
    //m = clazz.getMethod("c_public2"); // 런타임 오류!
  }

}





