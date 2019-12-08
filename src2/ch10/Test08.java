// 생성자 - 정의하는 방법
package ch10;

class Monitor6 {
  int bright; // 밝기 (0% ~ 100%)
  int contrast = 50; // 명암 (0% ~ 100%)
  int widthRes; // 해상도 너비
  int heightRes = 1080; // 해상도 높이
  
  // 생성자 형식
  //    클래스명(파라미터, 파라미터, ...) { ... }
  // => 생성자는 리턴 타입이 없다.
  // 
  // 생성자의 목적
  // => 인스턴스를 정상적으로 사용할 수 있도록 
  //    인스턴스 메서드가 사용하는 인스턴스 필드의 값을 
  //    유효한 값으로 설정하는 기능을 수행한다.
  // 
  // 생성자의 호출 절차
  // => 인스턴스 생성 => 인스턴스 필드 초기화 문장 실행 => 인스턴스 블록 실행 => 생성자 실행
  //
  // 생성자 호출하는 방법
  //    new 클래스명(생성자의 파라미터 값, 생성자의 파라미터 값, ....);
  //
  // 기본 생성자(default constructor)
  // => 파라미터가 없는 생성자이다.
  //
  Monitor6() {
    this.bright = 50;
    this.contrast = 50;
    this.widthRes = 2560;
    this.heightRes = 1200;
  }
  
  public void on() {
    // 모니터를 켜면 bright, contrast, widthRes, heightRes 값에 맞춰서 
    // LCD의 불을 밝힌다.
    System.out.println("화면을 출력한다.");
  }
}

public class Test08 {
  public static void main(String[] args) {
    // 인스턴스 생성
    
    // 1) 인스턴스를 생성한 후 호출될 생성자를 지정해야 한다.
    // => 지정하지 않으면 컴파일 오류가 발생한다.
    //new Monitor6; // 컴파일 오류!
    
    // 2) 파라미터가 없는 생성자를 호출할 경우 괄호를 표시한다.
    new Monitor6(); // OK
    
    // 3) 파라미터 값을 받지 않는 생성자를 호출할 때 값을 주면 컴파일 오류가 발생한다.
    //new Monitor6(100); // 컴파일 오류!
    
  }
}
















