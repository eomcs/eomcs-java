package ch18.g1;

public class Test01 {

  public static void main(String[] args) {
    
    // 인터페이스에 선언한 필드는 public static final 이기 때문에 바로 사용할 수 있다.
    System.out.println(A.V1);
    System.out.println(A.V2);
    
    // final이다 
    //A.V2 = 300; // 컴파일 오류!
  }

}
