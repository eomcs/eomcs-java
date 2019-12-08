// Wrapper 클래스 : 사용 전
package ch11;

public class Test09_2 {
  public static void main(String[] args) {
    // Wrapper 클래스의 가장 큰 목적!
    // => primitive 값을 포함하여 모든 값을 쉽게 주고 받기 위함이다.
    
    // Wrapper 클래스를 사용하지 않으면 다음과 같이 각 타입의 값을 처리할 메서드를 
    // 여러 개 만들어야 한다.
    printInt(100);
    printFloat(3.14f);
    //printInt(3.14f); // 컴파일 오류!
  }
  
  static void printInt(int i) {
    System.out.println("값 => " + i);
  }
  
  static void printFloat(float f) {
    System.out.println("값 => " + f);
  }
}
















