// 메서드 오버로딩(overloading) - 주의!
package com.eomcs.oop.ex06.b;

public class Exam04 {
    public static void main(String[] args) {
        // 자식 클래스에서 부모 클래스에 있는 메서드와 같은 이름의 메서드를 만들어도
        // (물론 파라미터 형식은 달라야 한다) 오버로딩이다.
        B.m(); // 부모 클래스의 m()을 호출
        B.m(100, 200, 300); // B 클래스의 m()을 호출
    }

}
