/* 소스 파일의 인코딩 지정하기
 * - 컴파일 할 때 소스 파일의 문자집합을 지정하는 방법을 알아보자!
 * - 컴파일 할 때 문자집합을 지정하지 않으면 어떻게 되는가?
 */

public class Exam02 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}

/*  [javac 컴파일러 사용법]
 *  > javac -encoding [문자집합] [소스파일명]
 *      => $프로젝트폴더> javac -encoding UTF-8 Exam02.java
 *      => 소스 파일을 저장할 때 사용한 문자표가 운영체제가 사용하는 기본 문자표(character set)와 다를 때,
 *         "-endcoding" 옵션을 붙여야 컴파일러가 제대로 컴파일을 수행할 수 있다.
 *      => -encoding 다음에 지정하는 문자표는 소스 파일을 저장할 때 사용한 문자표 이름이다.
 *      
 *
 */



