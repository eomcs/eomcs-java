// reflection API - 메서드의 상세정보 꺼내기
package ch27.b;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

// 어떤 클래스나 인터페이스의 스태틱 멤버를 import 할 수 있다.
import static java.lang.reflect.Modifier.*;

public class Test05_2 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = String.class;
    
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      System.out.printf("메서드명: %s.%s\n", 
          m.getDeclaringClass().getSimpleName(), 
          m.getName());
      
      
      Parameter[] params = m.getParameters();
      System.out.printf("파라미터: %d\n", m.getParameterCount());
      for (Parameter p : params) {
        //Class<?> parameterTypeInfo = p.getType();
        //String parameterTypeName = parameterTypeInfo.getName();
        System.out.printf("    %s:%s\n", 
            p.getName(), // 파라미터명
            p.getType().getName() // 파라미터 타입명
        );
      }
      
      System.out.println("리턴 타입:");
      System.out.printf("    %s\n", m.getReturnType().getName());
      
      
      System.out.println("modifier:");
      int modifiers = m.getModifiers();
      if (Modifier.isPublic(modifiers))//(modifiers & Modifier.PUBLIC) == Modifier.PUBLIC)
        System.out.println("    public");
      else if ((modifiers & PROTECTED) != 0)
        System.out.println("    protected");
      else if ((modifiers & PRIVATE) != 0)
        System.out.println("    private");
      
      
      //if ((modifiers & STATIC) != 0)
      if (Modifier.isStatic(modifiers))
        System.out.println("    static");
      
      if ((modifiers & FINAL) != 0)
        System.out.println("    final");

      System.out.println("-------------------------------------");
    }
    
    
  }

}





