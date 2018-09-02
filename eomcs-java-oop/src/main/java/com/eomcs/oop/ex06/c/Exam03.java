// 오버라이딩(overriding) - 오버라이딩 문법 사용!
package com.eomcs.oop.ex06.c;

public class Exam03 {
    public static void main(String[] args) {
        A3 obj1 = new A3();
        obj1.name = "홍길동";
        obj1.age = 20;
        
        // A3 클래스에서 A 클래스의 print() 메서드를 재정의 했기 때문에
        // 다음 호출하는 메서드는 A3의 print() 메서드가 된다.
        obj1.print();
    }
}

// 오버라이딩(overrding)?
// => 부모로부터 상속 받은 메서드 중에서 자신(서브클래스)의 역할에 맞지 않는 메서드가 있다면,
//    자신(서브클래스)의 역할에 맞춰 상속받은 메서드를 재정의하여 
//    프로그래밍의 일관성을 확보하는 문법이다.








