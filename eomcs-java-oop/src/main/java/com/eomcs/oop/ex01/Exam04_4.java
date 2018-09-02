// 클래스 사용 - import
package com.eomcs.oop.ex01;

// import 는 package 선언 다음에 두어야 한다.
import step03.test.B;

public class Exam04_4 {
    public static void main(String[] args) {
        
        // 다른 패키지의 클래스 사용
        // => 패키지 이름을 항상 붙여야 한다.
        //    그래야만 컴파일러가 해당 클래스를 찾을 수 있다.
        step03.test.B v1;
        v1 = new step03.test.B();

        // 그런데 다른 패키지의 클래스를 사용할 때 마다 
        // 매번 위와 같이 패키지명을 적는다면 너무 코드가 길어진다.
        // 해결책!
        // => 소스 파일 상단에 클래스의 패키지 정보를 미리 적어둔다.
        // => import 패키지명.클래스명;
        B v2;
        v2 = new B();
    }
}
