/* .class 파일을 분리하기
 * - .class 파일을 별도의 폴더로 분리하는 방법을 알아보자.
 * - 이렇게 하는 이유는 무엇인가?
 */

public class Exam03 {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}

/*  [javac 컴파일러 사용법]
 *  > javac [소스파일명]
 *      => $프로젝트폴더> javac Exam03.java
 *      => Exam03.java 소스 파일이 있는 폴더에 .class 파일을 생성한다.
 *      
 *  > javac -d [폴더명] [소스파일명]
 *      => $프로젝트폴더> javac -d bin Exam03.java
 *      => -d 옵션으로 지정한 폴더에 .class 파일을 둔다.
 *      => 이렇게 클래스 파일을 별도의 폴더에 분리해두면 나중에 배포 파일(.jar)을 만들 때 .class 파일을 추출하기가 편하다.
 *  
 *  [java JVM 사용법]
 *  > java [클래스명]
 *      => $프로젝트폴더> java Exam03
 *      => JVM을 실행하는 현재 폴더에서 Exam03.class 파일을 찾는다.
 *      => 만약 없다면, CLASSPATH 환경 변수에 지정된 폴더에서 Exam03.class 파일을 찾는다.
 *      
 *  > java -classpath [클래스_파일이_있는_경로] [클래스파일명]
 *  > java -cp [클래스_파일이_있는_경로] [클래스파일명]
 *      => $프로젝트폴더> java -classpath bin Exam03
 *      => $프로젝트폴더> java -cp bin Exam03
 *      => -classpath 또는 -cp 옵션에 지정된 폴더에서 Exam03.class 파일을 찾는다.
 *         
 *
 */



