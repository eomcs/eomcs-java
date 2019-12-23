// 초기화 블록 - 스태틱 블록의 목적
package com.eomcs.oop.ex03;

public class Exam0650 {
    
    public static class A {
        static float pi;
        static float area(int r) {
            return 2 * pi * r;
        }
        
        // 스태틱 블록은 클래스 멤버를 사용하기 전에 유효한 값으로 초기화시키는 것이 목적이다.
        static {
            pi = 3.14159f;
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(A.area(25));
    }
}





