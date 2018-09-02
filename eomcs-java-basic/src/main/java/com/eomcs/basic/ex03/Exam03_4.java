// 변수 - 변수 선언과 값 저장
package com.eomcs.basic.ex03;

public class Exam03_4 {
    public static void main(String[] args) {
        // 변수를 선언한 후 값 저장
        // - 즉 모든 메모리를 준비한 후 값 저장하기
        int a1, a2;
        a1 = 100;
        a2 = 200;
        System.out.println(a1);
        System.out.println(a2);

        // 변수 선언과 동시에 값을 저장하기 
        // - 메모리를 준비한 후 즉시 값으로 초기화시키기
        int b1 = 100, b2 = 200;
        System.out.println(b1);
        System.out.println(b2);

        // 물론 여러 개의 변수를 나열할 때,
        // 모든 변수의 값을 초기화 할 필요는 없다.
        int c1 = 100, c2, c3 = 300, c4;
        System.out.println(c1);

        // 메모리를 준비한 후 단 한 번이라도 값을 넣은 후에 사용해야 한다.
        // 만약 c2나 c4처럼 메모리를 어떤 값으로 초기화시키지 않은 상태에서
        // 출력하거나 사용하려하면 컴파일 오류가 발생한다!
        //System.out.println(c2); // 컴파일 오류!
        
        System.out.println(c3);

        //System.out.println(c4); // 컴파일 오류!
    }
}
