/* 다른 소스 파일의 코드를 참조할 때 컴파일하는 방법
 * - 다른 파일에 있는 자바 코드를 사용할 때 컴파일 하는 방법을 알아보자. 
 * - 그냥 일반적인 방법으로 컴파일 한다면 어떻게 되는가? 
 */

public class Exam05 {
    public static void main(String[] args) {
        Exam05Other.greeting(); // Exam05Other.java 파일에 있는 코드를 사용한다.
    }
}

/*  [javac 컴파일러 사용법]
 *  > javac -sourcepath [다른_소스파일이_있는_폴더의_경로] [소스파일경로_및_소스파일명]
 *      => 예) javac -sourcepath src/main/java src/main/java/Exam01.java
 *      => 만약 Exam01.java 파일에서 다른 소스 파일에 있는 코드를 사용했다면,
 *         그 소스 파일이 있는 위치를 -sourcepath 옵션으로 알려줘야 제대로 컴파일을 수행할 수 있다.
 *   
 *
 */




