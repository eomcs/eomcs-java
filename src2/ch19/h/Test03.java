// Lambda 문법 - 람다 파라미터 I
package ch19.h;

public class Test03 {
  
  static interface Player {
    void play(String name);
  }
  
  public static void main(String[] args) {
    // 1) 파라미터는 괄호() 안에 선언한다.
    Player p1 = (String name) -> System.out.println(name + " 님 환영합니다.");
    p1.play("홍길동");
    
    // 2) 파라미터 타입을 생략할 수 있다.
    Player p2 = (name) -> System.out.println(name + " 님 환영합니다.");
    p2.play("홍길동");
    
    // 3) 파라미터가 한 개일 때는 괄호도 생략할 수 있다.
    Player p3 = name -> System.out.println(name + " 님 환영합니다.");
    p3.play("홍길동");
  }
}









