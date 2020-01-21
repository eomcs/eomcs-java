package com.eomcs.basic.ex05;

//# 비트 이동 연산자 : 응용 III
//
public class Exam0470 {
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

    // 정수 값에서 특정 비트의 값만 검사하는 방법
    //   0000 0000 0001 0111 (여러 프로그래밍 언어에 대해 설정된 값)
    //
    // TypeScript 언어를 할 줄 아는지 검사하기
    //     0000 0000 0001 0111
    //   & 0000 0001 0000 0000 (TypeScript 값을 조사하려는 값과 AND 한다.)
    //   ----------------------  (조사하려는 값에 0을 AND 하면 무조건 0으로 만든다.)
    //     0000 0000 0000 0000   (조사하려는 값에 1을 AND 하면 원래 값 그래도 리턴한다.)
    //
    // AND 결과 값을 검사 값과 같은지 비교하면 된다.
    //     0000 0000 0000 0000 (결과값)
    //     0000 0001 0000 0000 (TypeScript 여부를 조사하는 값)
    // => 결과 값과 조사한 값이 같지 않으면 해당 비트가 0이라는 의미다.

    System.out.printf("Java      : %b\n", (d1 & JAVA) == JAVA);
    System.out.printf("C         : %b\n", (d1 & C) == C);
    System.out.printf("C++       : %b\n", (d1 & CPP) == CPP);
    System.out.printf("Python    : %b\n", (d1 & PYTHON) == PYTHON);
    System.out.printf("PHP       : %b\n", (d1 & PHP) == PHP);
    System.out.printf("Kotlin    : %b\n", (d1 & KOTLIN) == KOTLIN);
    System.out.printf("Groovy    : %b\n", (d1 & GROOVY) == GROOVY);
    System.out.printf("JavaScript: %b\n", (d1 & JAVASCRIPT) == JAVASCRIPT);
    System.out.printf("TypeScript: %b\n", (d1 & TYPESCRIPT) == TYPESCRIPT);
    System.out.printf("Go        : %b\n", (d1 & GO) == GO);
    System.out.printf("SQL       : %b\n", (d1 & SQL) == SQL);
    System.out.printf("R         : %b\n", (d1 & R) == R);
  }
}
