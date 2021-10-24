// Lambda 문법 - 익명 클래스 vs 람다
package com.eomcs.oop.ex12;

public class Exam0110 {

  // 다음과 같이 추상 메서드가 한 개 있는 인터페이스를 "functional interface"라고 부른다.
  // => 이런 경우에 람다 문법을 사용할 수 있다.
  // => 인터페이스는 static 을 붙이지 않아도 static 멤버가 사용할 수 있다.
  interface Player {
    void play();
  }

  public static void main(String[] args) {

    // 익명 클래스로 인터페이스 구현하기
    Player p1 = new Player() {
      @Override
      public void play() {
        System.out.println("익명 클래스");
      }
    };
    p1.play();

    // 람다 문법으로 인터페이스 구현하기
    // => 메서드 한 개짜리 인터페이스를 좀 더 간결하게 구현하기 위해 만든 문법이다.
    // => 뻔한 코드 생략!
    Player p2 = () -> {
      System.out.println("람다");
    };
    p2.play(); // 컴파일러가 만든 메서드를 호출하는 코드로 바뀐다. => lambda$0();
  }
}

// 익명 클래스와 .class 파일 
// => 자바의 nested class 는 모두 별도의 .class 파일을 갖는다.
// => 위의 main()에 정의된 로컬 익명 클래스는 다음과 같은 이름의 .class 파일로 컴파일 된다.
//      Exam0110$1.class
//
// 람다와 .class 파일 
// => 람다는 해당 클래스의 멤버 메서드로 정의된다. 
// => 즉 별도의  .class 파일을 생성하지 않는다.
// => 람다 문법이 초기에 등장했을 때는 익명 클래스로 변환되었다.
// => 그러나 최근에는 그냥 멤버 메서드로 변환된다.
// => 예) 
// 원래의 자바코드:
//    Player p2 = () -> {
//      System.out.println("람다");
//    };
//
// 컴파일러가 변환한 코드:
//    private static synthetic void lambda$0();
//    0  getstatic java.lang.System.out : java.io.PrintStream [33]
//    3  ldc <String "람다"> [39]
//    5  invokevirtual java.io.PrintStream.println(java.lang.String) : void [41]
//    8  return
//      Line numbers:
//        [pc: 0, line: 27]
//        [pc: 8, line: 28]
//
// => 람다를 호출하는 코드는 자동 생성된 메서드를 호출하는 코드로 변환된다.
//
