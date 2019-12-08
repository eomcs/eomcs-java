// Lambda 문법 - 람다 리턴 
package ch19.h;

public class Test05 {
  
  static interface Calculator {
    int compute(int a, int b);
  }
  
  public static void main(String[] args) {
    // 1) 리턴 값은 return 명령을 사용하여 처리한다.
    Calculator c1 = (a, b) -> { return a + b; };
    System.out.println(c1.compute(10, 20));
    
    // 2) 한 문장으로 된 표현식(=값을 리턴하는 한 문장의 코드)을 경우 괄호 생략할 수 있다. 
    Calculator c2 = (a, b) -> a - b;
    System.out.println(c2.compute(10, 20));
    
    // Math.max()는 int 값을 리턴한다. 그래서 이 메서드를 호출하는 문장은 표현식이다.
    Calculator c3 = (a, b) -> Math.max(a, b);
    System.out.println(c3.compute(10, 20));
    
    // 값을 리턴해야 하는데 람다 문장에서 값을 리턴하지 않으면 컴파일 오류!
    //Calculator c4 = (a, b) -> System.out.println(a + ",", b); // 컴파일 오류!
    //System.out.println(c4.compute(10, 20));
  }
  
}









