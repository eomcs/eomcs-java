// 생성자 - 생성자 호출 막기
package com.eomcs.oop.ex03;

class X {
    // 생성자의 접근 범위를 private으로 설정하면 외부에서 접근할 수 없기 때문에
    // 결국은 생성자를 호출하지 못하는 상태로 만들 수 있다.
    private X() {
        System.out.println("X()");
    }
}

public class Exam0440 {
    public static void main(String[] args) {
        X obj1;
        
        // 생성자의 접근이 막혀있기 때문에 생성자를 호출할 수 없다.
        // 따라서 new 명령으로 객체를 생성할 수 없도록 만든다.
        //obj1 = new X(); // 컴파일 오류!
    }
}








