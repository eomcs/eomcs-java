package com.eomcs.basic.ex05;

//# 비트 이동 연산자 : 응용 III
//
public class Exam0471 {
  public static void main(String[] args) {
    final int JAVA          = 0x0001; // 0000 0000 0000 0001
    final int C             = 0x0002; // 0000 0000 0000 0010
    final int CPP           = 0x0004; // 0000 0000 0000 0100
    final int PYTHON        = 0x0008; // 0000 0000 0000 1000
    final int PHP           = 0x0010; // 0000 0000 0001 0000
    final int KOTLIN        = 0x0020; // 0000 0000 0010 0000
    final int GROOVY        = 0x0040; // 0000 0000 0100 0000
    final int JAVASCRIPT    = 0x0080; // 0000 0000 1000 0000
    final int TYPESCRIPT    = 0x0100; // 0000 0001 0000 0000
    final int GO            = 0x0200; // 0000 0010 0000 0000
    final int SQL           = 0x0300; // 0000 0100 0000 0000
    final int R             = 0x4000; // 0000 1000 0000 0000

    // Java와 C, C++, JavaScript를 할 줄 아는 개발자의 정보를 설정하라!
    int d1 = JAVA | C | CPP | JAVASCRIPT; // 0000 0000 1000 0111

    // 값이 같지 않으면 어차피 결과는 0이다.
    // 그래서 다음과 같이 검사해도 된다.
    System.out.printf("Java      : %b\n", (d1 & JAVA) != 0);
    System.out.printf("C         : %b\n", (d1 & C) != 0);
    System.out.printf("C++       : %b\n", (d1 & CPP) != 0);
    System.out.printf("Python    : %b\n", (d1 & PYTHON) != 0);
    System.out.printf("PHP       : %b\n", (d1 & PHP) != 0);
    System.out.printf("Kotlin    : %b\n", (d1 & KOTLIN) != 0);
    System.out.printf("Groovy    : %b\n", (d1 & GROOVY) != 0);
    System.out.printf("JavaScript: %b\n", (d1 & JAVASCRIPT) != 0);
    System.out.printf("TypeScript: %b\n", (d1 & TYPESCRIPT) != 0);
    System.out.printf("Go        : %b\n", (d1 & GO) != 0);
    System.out.printf("SQL       : %b\n", (d1 & SQL) != 0);
    System.out.printf("R         : %b\n", (d1 & R) != 0);


  }
}
