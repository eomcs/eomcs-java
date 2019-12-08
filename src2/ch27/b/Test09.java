// reflection API - 클래스가 상속 받은 수퍼 클래스 알아내기
package ch27.b;

public class Test09 {

  public static void main(String[] args) throws Exception {
    
    Class<?> clazz = C.class;
    
    Class<?> superClass = clazz.getSuperclass();
    System.out.println(superClass.getName());
    System.out.println("----------------------------");
    
    printSuperClassName(clazz, 0);
  }
  
  static void printSuperClassName(Class<?> clazz, int level) {
    printIndent(level);
    
    System.out.println(clazz.getName());
    if (clazz == Object.class)
      return;
    
    printSuperClassName(clazz.getSuperclass(), level + 1);
  }

  static void printIndent(int level) {
    for (int i = 0; i < level; i++)
      System.out.print("  ");
  }
}





