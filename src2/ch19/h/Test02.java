// Lambda 문법 - 람다 body 
package ch19.h;

public class Test02 {
  
  static interface Player {
    void play();
  }
  
  public static void main(String[] args) {
    // 1) 한 문장일 때는 중괄호를 생략할 수 있다.
    Player p1 = () -> System.out.println("테스트1");
    
    // 2) 물론 중괄호를 명확히 적어도 된다.
    Player p2 = () -> {System.out.println("테스트1");};
  }
}









