package com.eomcs.basic.ex07;

//# 메서드 : 가변 파라미터의 단점
//
public class Exam0270 {

  // 가변 파라미터는 여러 개 선언할 수 없다.
  // => 아규먼트의 시작과 끝을 구분할 수 없다.
  //    예) m1("aaa", "bbb", "aaa@test.com", "bbb@test.com");
  //    어느 값이 names 배열에 들어가고, 어느 값이 emails 배열에 들어가는가?
  //static void m1(String... names, String... emails) {} // 컴파일 오류!
  //static void m1(String... names, int a, String... emails) {}// 컴파일 오류!
  // 위의 메서드는 값을 구분할 수 있을 것 같은데?
  // => 사람들은 쉽게 구분할 수 있다. 
  //    그러나 컴파일러가 이런 상황을 구분하려면 굉장히 복잡해진다.
  // => 그래서 가변 파라미터라는 문법의 이점은 사용하되
  //    너무 복잡한 사용법은 지양하기 위해서 
  //    사용 방법을 간단히 한 것이다.
  // m1("aaa", "bbb", 100, "ccc", "ddd", "eee");

  // 가변 파라미터는 반드시 맨 끝에 와야 한다.
  // => 아규먼트의 시작과 끝을 구분할 수 없다.
  //    예) m2("aaaa");
  //static void m2(String... names, String a) {} // 컴파일 오류!
  //static void m2(String... names, int a) {} // 컴파일 오류!
  static void m2(int a, String... names) {} // OK!

  // 배열 파라미터는 여러 개 선언할 수 있다.
  static void x1(String[] names, String[] emails) {}

  // 배열 파리미터는 순서에 상관 없다.
  static void x2(String[] names, int a) {}

  public static void main(String[] args) {
    // 컴파일 확인하라!
  }
}

// 결론!
// - 메서드에 가변 파라미터는 한 개만 사용할 수 있다.
// - 가변 파라미터는 반드시 맨 뒤에 와야 한다.
// - 그 이유는 복잡한 사용을 막기 위해!









