// main() 
package com.eomcs.basic.ex01;

public class Exam02 {
    public static void main(String[] args) {
        System.out.println("src/step01/Exam02");
    }
}

// => 명령창에서 JVM이 바로 실행할 수 있는 클래스는
//    main() 명령어 블록을 가지고 있는 클래스이다.
//
// => JVM이 클래스를 실행하는 과정
//    > java -classpath bin step01.Exam02
//
//    1) 실행할 클래스 파일을 CLASSPATH에 등록된 폴더에서 찾는다.
//    2) 유효한 bytecode 인지 검사한다.
//    3) 메모리에 bytecode를 적재(load)한다.
//    4) public static void main(String[] args) {} 블록을 찾는다.
//    5) main() 블록에 들어 있는 코드를 실행한다.