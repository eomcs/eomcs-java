// reflection API - 클래스가 구현한 인터페이스 알아내기
package ch27.b;

public class Test08 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = C.class;
    
    Class<?>[] interfaces = clazz.getInterfaces();
    for (Class<?> c : interfaces) {
      System.out.println(c.getName());
    }
  }

}





