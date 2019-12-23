// 초기화 블록 - 인스턴스 블록
package com.eomcs.oop.ex03;

public class Exam0610 {
    static class A {
        int a;
        int b;
        
        { // 아무 이름 없이 선언하는 블록이 "초기화 블록"이다.
            // 인스턴스를 생성한 후, 생성자를 호출하기 전에 자동으로 실행한다.
            System.out.println("{}11111");
        }
        
        A() {
            System.out.println("A()");
        }
        
        { // 초기화 블록은 여러 개 정의할 수 있다.
          // 순서대로 호출된다.
            System.out.println("{}22222");
            // 이렇게 초기화 블록을 나눠서 여러 개 정의하면 코드를 유지보수하기 어렵다.
            // 할 수는 있지만 이렇게 하지 말라!
            // 만약 초기화 블록을 정의한다면 한 개만 하라!
        }
    }
    public static void main(String[] args) {
        A obj1 = new A();
    }
}





