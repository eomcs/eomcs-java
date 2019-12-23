// 변수 초기화 - 초기화 문장과 생성자의 실행 순서
package com.eomcs.oop.ex03;

public class Exam0530 {
    static class A {
        int a = 200;
        
        // 생성자는 초기화 문장을 실행한 후에 호출된다.
        A() {
            this.a = 300;
        }
    }
    public static void main(String[] args) {
        A obj1 = new A();
        System.out.println(obj1.a);
    } 
}




