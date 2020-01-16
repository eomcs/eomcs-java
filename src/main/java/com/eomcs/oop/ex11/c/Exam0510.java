// 스태틱 멤버와 import 
package com.eomcs.oop.ex11.c;

import static com.eomcs.oop.ex11.c.Category2.appliance.*;
import static com.eomcs.oop.ex11.c.Category2.book.*;
import static com.eomcs.oop.ex11.c.Category2.computer.*;

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
