/* 컴파일과 실행 
 * - 자바 소스 파일을 컴파일하고 실행하는 방법을 알아보자!
 */

public class Exam01 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}

/*  [컴파일 및 실행 절차]
 *  1) .java 파일을 컴파일하여 .class 파일을 생성한다.
 *  2) .class 파일을 실행한다.
 *   
 *  [컴파일(compile)]
 *  - 자바로 작성한 명령어를 JVM이 이해할 수 있는 기계어(바이트코드)로 바꾸는 것.
 * 
 *  [소스 파일(source file)]
 *  - 개발자가 작성한 명령어 파일.
 *  - ".java" 파일 확장자를 가진다.
 * 
 *  [바이트코드(bytecode)]
 *  - 컴파일을 수행하여 생성한 파일.
 *  - ".class" 파일 확장자를 가진다.
 *  - JVM이 실행하는 파일.
 *  - 컴파일을 하면 기계가 이해할 수 있는 명령어로 변환하는데 이 기계어 명령을 "바이트코드"라 부른다.
 *  - portable code 또는 p-code 라고도 부른다.
 * 
 *  [바이트코드라는 용어의 유래]
 *  - 대부분의 기계어 명령(instruction sets)이 1바이트의 동작코드(opcode; operation code)와 0개 이상의 파라미터로 구성되는데 
 *    이 때문에 "바이트코드"라는 이름이 붙여졌다.
 *
 *  [컴파일러]
 *  - 컴파일을 수행하는 프로그램.
 *  - 자바 소스 파일을 분석하여 바이트코드를 생성한다.
 *      .java(소스파일) ==> .class(바이트코드 파일)
 *  - 사용법
 *      > $JAVA_HOME/bin/javac 소스파일명
 *    예) 
 *      $프로젝트폴더> "c:\Program Files\Java\jdk1.8.0_144\bin\javac" Exam01.java     
 * 
 *
 *  [자바가상머신(JVM; Java Virtual Machine)]
 *  - 바이트코드로 작성된 .class 파일을 실행하는 프로그램.
 *  - VM과 유사한 일을 하는 프로그램들을 부르는 용어: player, interpreter, engine 등
 *    예) MP3 플레이어, 동영상 플레이어, 플래시 플레이어, 코볼 인터프리터, JSP 엔진 등
 *  - 사용법
 *      > $JAVA_HOME/bin/java 클래스명
 *    예) 
 *      $프로젝트폴더> "c:\Program Files\Java\jdk1.8.0_144\bin\java" Exam01 
 *
 *  [PATH 환경변수]
 *  - 자바 컴파일러와 JVM 파일이 들어 있는 폴더를 운영체제의 PATH 환경변수에 등록해 놓으면 
 *    컴파일하거나 실행할 때 긴 파일 경로를 적을 필요가 없다. 
 *  - Windows, Linux, macOS 운영체제에 맞춰 설정하라!
 */



