/* 자바 패키지
 * => 서로 밀접하게 관련된 클래스들을 같은 그룹으로 묶는 문법
 *    소스 파일에 첫 번째 명령으로 다음을 추가하라
 *    package 그룹명.그룹명.그룹명...;
 * => 그룹명과 소스 파일이 있는 위치가 일치되게 하라.
 *    그래야지 소스 파이을 찾고 관리하기 쉬워진다.
 * => 컴파일 한 후 .class 파일은 무조건 패키지 이름과 같은 
 *    폴더에 있어야 한다. 규칙이다.
 * => 즉 복잡하게 생각하지 말고 패키지 이름과 같은 폴더에
 *    소스 파일과 클래스 파일을 두어라.
 * => 예) Hello 클래스의 패키지가 다음과 같다면,
 *    package a.b.c.d;
 *    class Hello {}
 * 
 * => 소스 파일의 위치
 *    src/main/java/a/b/c/d/Hello.java
 * => 컴파일
 *    > javac -d bin -encoding UTF-8 src/main/java/a/b/c/d/Hello.java
 * => 컴파일 결과
 *    bin/a/b/c/d/Hello.class
 * => 실행
 *    > java -cp bin [패키지명을포함한클래스명]
 *    > java -cp bin a.b.c.d.Hello
 * => 주의 사항!
 *    패키지에 소속된 클래스를 실행할 때는 반드시 패키지명과 클래스명을 함께 지정해야 한다.
 *    다음과 같이 패키지명을 -cp에 포함시키고 클래스명만 지정한다면 실행오류이다.
 *    > java -cp bin/a/b/c/d Hello    <==== 실행오류!
 *     
 */

package com.eomcs.basic.ex01;

public class Exam01 {

    public static void main(String[] args) {
        System.out.println("Hello!");
    }

}
