package com.eomcs.oop.ex06.c;

public class A3 extends A {
    int age;
    
    // 상속 받은 메서드가 서브 클래스의 맞지 않다면 
    // 서브 클래스의 역할에 맞춰서 재정의 하라!
    // 재정의?
    // => 즉 부모의 메서드와 똑같은 메서드를 정의하는 것이다.
    // => 그래서 부모의 메서드를 덮어쓰는(override) 것이다.
    // => 이 메서드를 호출하는 개발자 입장에서는 A 클래스와 같은 메서드를 사용하기 때문에  
    //    별다른 구분없이 사용할 수 있다.
    void print() {
        System.out.printf("'%s(%d)'님 반갑습니다!\n", this.name, this.age);
    }
}
