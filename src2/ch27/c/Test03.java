// reflection API - 메서드 호출하기 II : 가변 파라미터 메서드 호출
package ch27.c;

import java.lang.reflect.Method;

public class Test03 {
  // 가변 파라미터(varargs; variable arguments)
  public static void print(String... names) {
    System.out.print("==> ");
    for (String name : names) {
      System.out.print(name + ",");
    }
    System.out.println();
  }
  
  public static void print2(String[] names) {
    System.out.print("==> ");
    for (String name : names) {
      System.out.print(name + ",");
    }
    System.out.println();
  }
  
  public static void print3(String name, int a, int b, int c) {
    System.out.printf("==> %s: %d\n", name, (a + b + c));
  }
  
  public static void main(String[] args) throws Exception {
    Method m1 = Test03.class.getMethod("print", String[].class);
    System.out.println(m1);
    m1.invoke(null, (Object) new String[] {"홍길동", "임꺽정", "유관순"});
    
    Method m2 = Test03.class.getMethod("print2", String[].class);
    System.out.println(m2);
    m2.invoke(null, (Object) new String[] {"홍길동", "임꺽정", "유관순"});
    
    Method m3 = Test03.class.getMethod("print3", String.class, int.class, int.class, int.class);
    System.out.println(m3);
    m3.invoke(null, "홍길동", 100, 90, 80);
    m3.invoke(null, new Object[] {"홍길동", 100, 100, 100});

    
  }
  
}






