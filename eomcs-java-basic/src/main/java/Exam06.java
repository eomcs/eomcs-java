/* 코드와 주석
 * - 자바 명령을 작성할 때 컴파일에 포함되지 않는 주석을 작성하는 방법을 알아보자.
 * - 주석을 붙이는 이유는 무엇인가?
 */

/* "multi-line comment"
   여러 줄에 걸쳐서 
   주석을 달고 싶을 때
   이와 같이 작성한다.*/ 

// "single-line comment" - 한 줄 주석을 달 때 사용한다.

/**
 * "java document comment(doc comment)"
 * - javadoc에서 HTML 문서를 만들 때 사용하는 주석이다.
 * - 이 주석에 작성한 내용을 HTML 문서에 넣는다.
 * - 보통 클래스나 메서드, 변수에 대해 붙인다.
 *  
 * @author eomjinyoung
 */

/**
 * 클래스에 대한 설명
 * @author eomjinyoung
 *
 */
public class Exam06 {
    /**
     * 변수에 대한 설명
     */
    static String message = "Hello, world!";
    
    /**
     * 메서드에 대한 설명
     * @param args
     */
    public static void main(String[] args) {
        // 코드를 이해하는 데 필요한 정보
        System.out.println(message);
    }

}
