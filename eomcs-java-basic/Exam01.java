//: ## 컴파일과 실행 
//: 자바 소스 파일을 컴파일하고 실행하는 방법을 알아보자!
//: 
//: 소스파일: 프로젝트/Exam01.java
public class Exam01 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}

//: ### 실습
//: 1) 컴파일
//: .java 파일을 컴파일하여 .class 파일을 생성한다.
//: ```
//: 프로젝트> javac Exam01.java
//: ```
//: 2) 실행  
//: .class 파일을 실행한다.
//: ```
//: 프로젝트> java Exam01
//: ``` 
//: 
//: ### 자바 소스 파일(source file)
//: - 개발자가 자바 언어로 작성한 명령어 파일이다.
//: - '.java' 파일 확장자를 가진다.
//:
//: ### 컴파일(compile)
//: - 자바로 작성한 명령어를 JVM이 이해할 수 있는 기계어로 바꾼다.
//: - 자바 소스 파일(.java)을 컴파일하면 기계어 파일(.class)을 생성한다.
//: 
//: ### 컴파일러
//: - 컴파일을 수행하는 프로그램.
//:
//: ### 바이트코드(bytecode)
//: - 컴파일을 통해 생성된 기계어를 "바이트코드"라 부른다.
//: - '.class' 파일 확장자를 가진다.
//: - JVM이 실행하는 파일이다. 
//: - 다른 말로 portable code 또는 줄여서 p-code 라고도 부른다.
//:
//: ### 바이트코드라는 용어의 유래
//: - 대부분의 기계어 명령(instruction sets)이 1바이트의 동작코드(opcode; operation code)와 0개 이상의 파라미터로 구성되는데 이 때문에 "바이트코드"라는 이름이 붙여졌다.
//:
//: ### 자바가상머신(JVM; Java Virtual Machine)
//: - 바이트코드로 작성된 .class 파일을 실행하는 프로그램.
//: - VM과 유사한 일을 하는 프로그램들을 부르는 용어: player, interpreter, engine, viewer 등
//: - 예) MP3 플레이어, 동영상 플레이어, 플래시 플레이어, 코볼 인터프리터, JSP 엔진, HWP 뷰어, PPT 뷰어 등

//: ### 자바 컴파일러 사용법
//: `%JAVA_HOME%\bin\javac 소스파일명`
//: 예)
//: ```
//: 프로젝트> "c:\Program Files\Java\jdk-9\bin\javac" Exam01.java   
//: ```  
//: ### JVM 사용법
//: `%JAVA_HOME%\bin\java 클래스명'
//: 예)
//: ``` 
//: 프로젝트> "c:\Program Files\Java\jdk-9\bin\java" Exam01 
//: ```
//:
//: ### PATH 환경변수
//: - 자바 컴파일러와 JVM 파일이 들어 있는 폴더를 운영체제의 PATH 환경변수에 등록해 놓으면 컴파일하거나 실행할 때 긴 파일 경로를 적을 필요가 없다. 
//: - 사용하는 운영체제에 맞춰 설정한다.
//:



