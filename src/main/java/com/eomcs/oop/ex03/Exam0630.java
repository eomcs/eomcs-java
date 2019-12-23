// 초기화 블록 - 초기화 순서
package com.eomcs.oop.ex03;

public class Exam0630 {
    
    static class A {
        // 3) 생성자 실행
        A() {
            a = 300;
        }
        
        // 2) 인스턴스 블록 실행 
        {
            a = 200;
        }
        
        // 1) 인스턴스 변수 생성 및 할당 연산자 실행(초기화 문장 실행)
        int a = 100;
    }
    
    public static void main(String[] args) {
        A obj1 = new A();
        System.out.println(obj1.a);
    }
}





