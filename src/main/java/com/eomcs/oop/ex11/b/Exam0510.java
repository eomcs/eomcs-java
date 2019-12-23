// 스태틱 멤버와 import 
package com.eomcs.oop.ex11.b;

// 스태틱 중첩 클래스 이름까지도 생략하여 단지 스태틱 변수명이나 스태틱 함수명만 사용하고 싶다면   
// 다음과 같이 import static을 사용하여 미리 컴파일러에게 알려줘라!
import static com.eomcs.oop.ex11.b.Category2.appliance.*;
import static com.eomcs.oop.ex11.b.Category2.computer.*;
import static com.eomcs.oop.ex11.b.Category2.book.*;

public class Exam0510 {
    public static void main(String[] args) {
        Product2 p = new Product2();
        
        // 위에서 import로 스태틱 멤버(변수,메서드,중첩클래스)가 들어 있는 
        // 바깥 클래스의 정보를 컴파일러에게 미리 알려줬기 때문에 
        // 다음과 같이 바깥 클래스와 중첩 클래스 이름을 모두 생략하고 쓸 수 있다.
        p.category = TV;
        p.category = NOVEL;
        p.category = MOUSE;
        
        p.name = "울트라비전 뷰";
        p.price = 2000000;
    }
}
