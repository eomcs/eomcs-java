package com.ohoracs.basic.ex03;

//# 문자의 리터럴 - 작은따옴표('')와 문자 코드
//- 문자 리터럴을 표현할 때 사용하는 작은 따옴표는 문자의 코드 값을 알려주는 도구이다.
//

public class Exam43 {
  public static void main(String[] args) {
    
    //유니코드를 직접 넘겨주기
    //단, 유니코드임을 알려주기 위해 (char)를 앞에 붙인다.
    System.out.println((char)65);

    //작은 따옴표를 사용하여 문자 코드를 넘겨주기
    //(char)붙일 필요가 없다.
    System.out.println('A');
    
    //작은 따옴표가 알려준 유니코드를 그대로 출력하기
    //정수를 의미하는 (int)를 앞에 붙인다.
    System.out.println((int)'A');
  }
}