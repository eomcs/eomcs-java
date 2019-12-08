// String 클래스 사용법 - String 인스터스에 있는 값을 비교하기 : equals()
package ch11;

public class Test04 {
  public static void main(String[] args) {
    
    String s1 = new String("Hello");
    String s2 = new String("Hello"); 
    
    if (s1 == s2) // 인스턴스가 같은 지 비교한다.
      System.out.println("s1 == s2");
    else
      System.out.println("s1 != s2");
    
    
    if (s1.equals(s2)) // 인스턴스에 있는 문자열이 같은지 비교한다.
      System.out.println("s1과 s2의 문자열이 같다.");
    else
      System.out.println("s1과 s2의 문자열이 다르다.");
    
    
    String s3 = "hello";
    String s4 = "HELLO";
    
    if (s3.equals(s4)) // 문자열의 데이터를 비교할 때 대소문자를 구분한다.
      System.out.println("s3.equals(s4)");
    
    if (s3.equalsIgnoreCase(s4)) // 대소문자를 구분하지 않는다.
      System.out.println("s3.equalsIgnoreCase(s4)");
  }
}
















