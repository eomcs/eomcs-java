/* 컴파일과 실행 */

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
 *      > "c:\Program Files\Java\jdk1.8.0_144\bin\javac" Exam01.java     
 * 
 *
 *  [자바가상머신(JVM; Java Virtual Machine)]
 *  - 바이트코드로 작성된 .class 파일을 실행하는 프로그램.
 *  - VM과 유사한 일을 하는 프로그램들을 부르는 용어: player, interpreter, engine 등
 *    예) MP3 플레이어, 동영상 플레이어, 플래시 플레이어, 코볼 인터프리터, JSP 엔진 등
 *  - 사용법
 *      > $JAVA_HOME/bin/java 클래스명
 *    예) 
 *      > "c:\Program Files\Java\jdk1.8.0_144\bin\java" Exam01 
 * 
 *  [javac 컴파일러 사용법]
 *  > javac [소스파일명]
 *      => 예) javac Exam01.java
 *      => Exam01.java 소스 파일이 있는 폴더에 .class 파일을 생성한다.
 *      
 *  > javac -d [폴더명] [소스파일명]
 *      => 예) javac -d bin Exam01.java
 *      => -d 옵션으로 지정한 폴더에 .class 파일을 둔다.
 *      
 *  > javac -encoding [문자집합] [소스파일명]
 *      => 예) javac -encoding UTF-8 Exam01.java
 *      => 소스 파일을 저장할 때 사용한 문자표가 운영체제가 사용하는 기본 문자표(character set)와 다를 때,
 *         "-endcoding" 옵션을 붙여야 컴파일러가 제대로 컴파일을 수행할 수 있다.
 *      => -encoding 다음에 지정하는 문자표는 소스 파일을 저장할 때 사용한 문자표 이름이다.
 *      
 *  > javac [소스파일이_있는_폴더경로/소스파일명]
 *      => 예) javac -d bin -encoding UTF-8 src/Exam01.java
 *      => 개발이 완료되면 .class 파일만 추출하여 .jar 파일에 묶어 배포한다.
 *         이를 위해 .class 파일을 보다 쉽게 추출할 수 있도록 별도의 폴더에 분리하는데, 
 *         소스 파일 또한 관리하기 쉽도록 별도의 폴더에 저장하는 것이 일반적이다.
 *  > javac -sourcepath [다른_소스파일이_있는_폴더의_경로] [소스파일경로_및_소스파일명]
 *      => 예) javac -sourcepath src src/Exam01.java
 *      => 만약 Exam01.java 파일에서 다른 소스 파일에 있는 코드를 사용했다면,
 *         그 소스 파일이 있는 위치를 -sourcepath 옵션으로 알려줘야 제대로 컴파일을 수행할 수 있다.
 *   
 *  [java JVM 사용법]
 *  > java [클래스명]
 *      => 예) java Exam01
 *      => JVM을 실행하는 현재 폴더에서 Exam01.class 파일을 찾는다.
 *      => 만약 없다면, CLASSPATH 환경 변수에 지정된 폴더에서 Exam01.class 파일을 찾는다.
 *      
 *  > java -classpath [클래스_파일이_있는_경로] [클래스파일명]
 *  > java -cp [클래스_파일이_있는_경로] [클래스파일명]
 *      => 예) java -classpath bin Exam01
 *      => 예) java -cp bin Exam01
 *      => -classpath 또는 -cp 옵션에 지정된 폴더에서 Exam01.class 파일을 찾는다.
 *         
 *
 */



