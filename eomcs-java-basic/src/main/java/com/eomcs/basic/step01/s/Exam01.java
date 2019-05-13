//# 패키지와 클래스
//클래스를 패키지로 묶는 방법과 패키지 클래스를 컴파일하고 실행하는 방법
// 
//## 패키지
//- 클래스를 관리하기 쉽게 서로 밀접하게 관련된 클래스를 그룹으로 분류하는 문법이다.
//- 패키지는 폴더로 표현한다.
//- 소스 파일의 첫 번째 문장으로 선언해야 한다.
//     package 패키지명.패키지명.패키지명;
//     예) package com.eomcs.basic.s;
//- 소스 파일 찾고 관리하기 쉽도록 패키지와 일치하는 폴더에 둔다.
//     eomcs-java-basic/src/main/java/패키지명/패키지명/패키지명/소스파일    
//     예) eomcs-java-basic/src/main/java/com/eomcs/basic/step01/s/Exam01.java
//- 컴파일러가 생성한 .class 파일은 무조건 패키지 이름과 같은 폴더에 있어야 한다.

package com.eomcs.basic.step01.s;

public class Exam01 {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

//## 실습
//1) 소스 디렉토리 생성
//- eomcs-java-basic/src/main/java/com/eomcs/basic/step01/s 폴더를 생성한다.
//
//2) 소스 파일 생성
//- Exam01.java 파일을 작성한다.
//
//3) 컴파일하기
//- 프로젝트 폴더에서 실행 할 때:
//    예) eomcs-java-basic$ javac -d bin/main src/main/java/com/eomcs/basic/step01/s/Exam01.java
//- 클래스 파일은 패키지 폴더에 생성된다.
//    예) eomcs-java-basic/bin/main/eom/eomcs/basic/step01/s/Exam01.class
//
//4) 실행하기
//- 프로젝트 폴더에서 실행 할 때:
//    java [-classpath | -cp] [루트 패키지가 있는 디렉토리 경로] [패키지 이름을 포함한 클래스 이름]
//    예) eomcs-java-basic$ java -cp bin/main com.eomcs.basic.step01.s.Exam01
//- 다음과 같이 . 대신에 / 를 사용해도 된다.
//    예) eomcs-java-basic$ java -cp bin/main com/eomcs/basic/step01/s/Exam01
//- 주의!
//  패키지에 소속된 클래스를 실행할 때는 반드시 패키지명과 클래스명을 함께 지정해야 한다.
//  다음과 같이 -cp에 패키지 경로를 지정한 후 클래스 이름으로 실행할 수 없다.
//    예) eomcs-java-basic$ java -cp bin/main/com/eomcs/basic/step01/s Exam01  <== 실행 오류!
