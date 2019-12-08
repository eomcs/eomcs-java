// 그 밖의 클래스 멤버들
package ch08;

import java.util.Scanner;

class My4 {
  // 클래스 필드 = 스태틱 필드(변수)
  // => 클래스가 로딩될 때 생성되는 변수
  // => 클래스 필드도 인스턴스 필드처럼 기본 값으로 자동 초기화 된다.
  //    정수(byte, short, int, long, char) => 0
  //    부동소수점(float, double) = 0.0
  //    논리(boolean) => false
  //    레퍼런스 => null
  // => 참고! 로컬 변수는 절대로 자동 초기화 되지 않는다.
  static int a;
  
  // 스태틱 블록 = 클래스 블록 
  // => 클래스가 로딩될 때 클래스 변수를 생성한 후 실행되는 블록
  // => 따라서 딱 한 번만 실행된다.
  // => 스태틱 필드를 초기화하는 코드를 둔다.
  // => 스태틱 메서드를 실행하는데 필요한 값을 준비하는 코드를 둔다.
  static {
    System.out.println("오호라!");
  }
  
  static {
    System.out.println("이것이 스태틱 블록이네! 여러 개를 선언할 수 있네!");
  }
  
  // 스태틱 메서드 = 클래스 메서드
  // => 클래스 이름으로 호출하는 함수
  static void m1() {
    System.out.println("나를 호출하셨군요!!!");
  }
  
}

public class Test04 {
  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    
    System.out.println("***************");
    
    // 레퍼런스를 선언할 때는 클래스가 로딩되지 않는다.
    // 따라서 스태틱 블록이 실행되지 않는다.
    My4 obj1;
    keyScan.nextLine();
    
    System.out.println("------------------");
    
    // 클래스가 로딩되는 때? 클래스 멤버(필드, 메서드)를 사용할 때!
    // - 클래스 필드를 사용할 때
    //My4.a = 300;
    //keyScan.nextLine();
    
    // - 클래스 메서드를 사용할 때
    My4.m1();
    keyScan.nextLine();
    
    My4.m1(); // 한 번 클래스가 로딩되면 다시 로딩되지 않는다. 따라서 스태틱 블록은 딱 한 번 실행된다.
  }
}

/*
# 클래스 로딩
1) 클래스를 외부 저장소에서 내부 저장소인 RAM으로 로딩한다.
2) 클래스의 바이트코드 유효성을 검사한다.
3) 클래스 내부에서 사용하는 모든 이름(변수명, 메서드명, 클래스명 등) 목록을 준비한다.
4) 클래스 필드를 찾아 생성한다.
5) 스태틱 블록이 있으면 순서대로 실행한다.

 */















