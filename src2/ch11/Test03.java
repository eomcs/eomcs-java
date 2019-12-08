// String 클래스 사용법 - 힙에 있는 스트링 인스턴스를 상수풀에도 만들기
package ch11;

public class Test03 {
  public static void main(String[] args) {
    
    String s1 = new String("Hello"); // 힙에 생성된다.
    String sx = "Hello";
    String s2 = s1.intern(); // s1이 가리키는 인스턴스를 상수풀에도 복제한다.
                             // 이미 상수풀에 있다면 기존의 객체를 리턴한다.
    String s3 = "Hello"; // 위에서 이미 만든 인스턴스 주소를 리턴한다.
    
    if (s1 == s2)
      System.out.println("s1 == s2");
    else
      System.out.println("s1 != s2");
    
    
    if (s2 == s3)
      System.out.println("s2 == s3");
    else
      System.out.println("s2 != s3");
    
    
    if (s2 == sx)
      System.out.println("s2 == sx");
    else
      System.out.println("s2 != sx");
    
    
  }
}
















