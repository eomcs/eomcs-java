package com.eomcs.oop.ex06.c;

public class D2 extends D {
    @Override
    void m() {
        System.out.println("D2의 m()");
    }
    
    void test() {
        this.m();
        
        // 만약 오버라이딩 하기 전에 메서드를 호출하고 싶다면?
        super.m();
    }
}






