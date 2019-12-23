// 캡슐화(encapsulation) - 접근 제한
package com.eomcs.oop.ex07.a;

public class Exam0120 {
    
    public static void main(String[] args) {
        // 환자 데이터를 등록해보자!
        Customer2 c1 = new Customer2();
        c1.name = "홍길동";
        c1.age = 300;
        c1.weight = 100;
        c1.height = -50;
        
        // Customer2에서는 인스턴스 변수의 접근을 private으로 막았기 때문에
        // 다른 클래스는 해당 인스턴스 변수에 접근할 수 없다.
        // 해결책?
        // => 이 클래스에서 제공하는 메서드를 사용하라!
        // => Exam01_3.java를 확인하라!
    }
}







