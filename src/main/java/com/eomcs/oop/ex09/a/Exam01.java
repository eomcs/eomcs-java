// Worker 구현체 사용
package com.eomcs.oop.ex09.a;

public class Exam01 {
    public static void main(String[] args) {
        // 인터페이스 레퍼런스
        // => 인터페이스를 구현한 클래스의 객체 주소를 저장하는 변수
        Worker w1 = new BlueWorker();
        
        // 인터페이스를 구현하지 않은 클래스의 인스턴스 주소를 저장할 수 없다. 
        //Worker w2 = new String();// 컴파일 오류!
        
        Worker w2 = new WhiteWorker();
        Worker w3 = new JubuWorker();
        
        // 인터페이스 구현체 호출
        w1.execute();
        w2.execute();
        w3.execute();
        
        // 역할?
        // caller : Exam01
        // callee : BlueWorker, WhiteWorker, JubuWorker
        // 규칙: Worker
        
    }
}



