// String 클래스 사용법 - 문자열 리터럴로 String 인스턴스 생성하기
package ch11;

public class Test02 {
  public static void main(String[] args) {
    
    String s1;
    s1 = "Hello"; // => new String("Hello") => 스트링 상수풀에 생성
    // String 레퍼런스에 문자열 리터럴을 할당하면 내부적으로 다음 코드로 바뀐다.
    //    s1 = new String("Hello");
    // 단 리터럴(값을 직접 작성한 것)로 String 인스턴스를 만들 때는 
    // 같은 문자열에 대해 중복해서 인스턴스를 만들지 않도록 
    // 내부적으로 관리한다.
    // 리터럴로 만든 String 인스턴스는 힙(heap)이 아닌 
    // 상수 문자열만 별도로 관리하는 영역(스트링 상수풀;String constant pool)에 생성한다.
    // 그래서 같은 문자열을 가진 인스턴스를 생성하지 않는다.
    //
    
    String s2 = "Hello"; // 이미 같은 문자열을 가진 인스턴스가 있기 때문에 기존 인스턴스 주소를 리턴.
                         // 왜? 메모리가 절약된다!
    
    if (s1 == s2)
      System.out.println("s1 == s2");
    else
      System.out.println("s1 != s2");
    
    String s3 = new String("Hello");
    
    if (s1 == s3)
      System.out.println("s1 == s3");
    else
      System.out.println("s1 != s3");
    
    
    // instanceof 연산자
    // => 레퍼런스가 가리키는 인스턴스가 특정 클래스의 인스턴스인지 확인하는 연산자이다.
    System.out.println(s1 instanceof String);
    System.out.println(s2 instanceof String);
    System.out.println(s3 instanceof String);
  }
}
















