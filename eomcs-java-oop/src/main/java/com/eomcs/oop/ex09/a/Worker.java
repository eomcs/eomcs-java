// 인터페이스 - caller와 callee 사이의 호출 규칙을 정의하는 문법
package com.eomcs.oop.ex09.a;

public interface Worker {
    
    // 호출 규칙?
    // => 메서드 형식을 의미한다.
    // => 메서드의 몸체는 정의하지 않는다.
    // => 메서드의 몸체는 이 규칙에 따라 만드는 클래스에서 정의하는 것이
    void execute();
}
