package com.eomcs.basic.ch03.solution;

//# 문자의 리터럴 - 문자 집합(character set)
//
//- 자바는 문자를 다룰 때 2바이트 값으로 다룬다.
//- 즉 각각의 문자는 규칙에 따라 0 ~ 65535까지의 값으로 메모리에 저장된다.
//- 각 문자에 대해 정의된 값을 '문자 코드(character code)'라 부른다.
//- 문자에 대해 문자 코드를 정의한 것을 '문자집합(chararacter set)'이라 부른다.
//- 자바가 사용하는 문자 집합은 '유니코드(Unicode)'이다.
//

public class Exam41 {
  public static void main(String[] args) {
    
    //문자 'A'의 문자 코드를 직접 사용하여 문자를 출력하기
    //- 유니코드 문자집합에서 문자 'A'의 문자 코드는 65 이다.
    //- println()에 숫자를 전달하면 일반 숫자인줄 알고 그대로 출력한다.
    System.out.println(65);

    //- println()에 전달하는 값이 일반적인 수가 아니라 문자 코드임을 지정해야 한다.
    System.out.println((char)65);

    //- 보통 문자 코드를 표현할 때 16진수를 사용한다.
    System.out.println((char)0x41);

    //한글 '가'를 문자 코드를 이용하여 출력하기
    System.out.println((char)0xAC00);
  }
}

//## 실습
//1) 컴파일하기
//   eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch03/solution/Exam41.java
//
//2) 실행하기
//   eomcs-java-basic$ java -cp bin/main com.eomcs.basic.ch03.solution.Exam41
//