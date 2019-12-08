// reflection API - 메서드 호출하기
package ch27.c;

import java.lang.reflect.Method;

public class Test02 {

  public static void main(String[] args) throws Exception {
    
    Student s1 = new Student("홍길동", "hong@test.com", true, 25);
    
    // 인스턴스 메서드 호출하기:
    // => setName() 메서드 꺼내기 
    //Method m1 = Student.class.getMethod("setName", String.class);
    //Method m1 = Class.forName("ch27.c.Student").getMethod("setName", String.class);
    Method m1 = s1.getClass().getMethod("setName", String.class);
    
    // => invoke(객체주소, 파라미터값, ...)
    m1.invoke(s1, "임꺽정"); // => s1.setName("임꺽정")
    
    System.out.println(s1);
    
    
    // 스태틱 메서드 호출하기
    Method m2 = s1.getClass().getMethod("greeting", 
        String.class, int.class, boolean.class);
    
    // => 스태틱 메서드는 인스턴스가 필요없다.
    // => 메서드의 리턴 값이 있다면 받으면 된다.
    //    단 타입 캐스팅 문법을 붙여야 한다.
    String str = (String) m2.invoke(null, "유관순", 17, true);
    System.out.println(str);
  }

}





