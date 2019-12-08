// 클래스 필드 공유
package ch08;

public class Test05 {
  
  static int a; // 클래스가 로딩될 때 Method Area에 a라는 이름의 변수를 만들라는 명령어다!
  
  public static void main(String[] args) {
   a = 100; // 같은 클래스에 소속된 멤버이기 때문에 클래스명을 생략해도 된다.
    m1(); // 같은 클래스에 소속된 멤버이기 때문에 클래스명을 생략해도 된다.
    m2();
    System.out.println(a);
  }
  
  static void m1() {
    a = 200;
  }
  
  static void m2() {
    a = 300;
  }
}
















