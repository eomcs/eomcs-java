// 클래스 공개, 비공개
package ch07;

public class Test10 {
  public static void main(String[] args) throws Exception {
    // 같은 패키지에 있는 클래스를 사용할 때는 
    // 공개, 비공개 모두 사용할 수 있다.
    Score10 s1 = new Score10();
    Score11 s2 = new Score11();
    
    // 공개된 패키지 멤버 클래스는 다른 패키지에서 사용할 수 있다.
    ch07.sub.Score12 s3 = new ch07.sub.Score12();
    
    // 공개되지 않은 패키지 멤버 클래스는 다른 패키지에서 사용할 수 없다.
    //ch07.sub.Score13 s4 = new ch07.sub.Score13(); // 컴파일 오류!
    
  }
}
















