// 리터럴(literal)
package com.eomcs.basic.ex02;

public class Exam01 {
    public static void main(String[] args) {
        // 정수 
        // => 4바이트 크기의 정수
        System.out.println(10);
        System.out.println(+10);
        System.out.println(-10);
        
        // => 8바이트 크기의 정수
        //    숫자 뒤에 l, L을 붙인다.
        System.out.println(10L);
        System.out.println(+10l);
        System.out.println(-10L);

        // 부동소수점
        // => 4바이트 크기
        //    숫자 뒤에 F, f를 붙인다.
        System.out.println(3.14F);
        System.out.println(3.14f);
        
        // => 8바이트 크기
        System.out.println(3.14);
        System.out.println(31.4e-1);

        // 문자열
        System.out.println("오호라..");

        // 한 개의 문자
        System.out.println('가');

        // 논리값 
        System.out.println(true);
        System.out.println(false);


    }
}

// 자바 코드로 값을 표현 것  
// 