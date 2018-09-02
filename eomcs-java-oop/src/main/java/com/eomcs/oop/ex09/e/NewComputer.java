package com.eomcs.oop.ex09.e;

public class NewComputer implements Computer {
    // 새 컴퓨터를 만들 때는 기존 규칙만 따라도 되고,
    // => 즉 compute() 메서드만 구현해도 되고
    public void compute() {
        System.out.println("새 컴퓨터..");
    }
    
    // 새로 추가한 규칙을 정의하는 것은 개발자 마음이다.
    // => 즉 나중에 추가한 touch() 메서드를 구현하는 것은 개발자 마음!
    public void touch() {
        System.out.println("오호라.. 터치가 되네. 이거 서피스 프로인가?");
    }

}






