// 멀티 스레드 적용 전
package ch24.a;

public class Test01 {

  public static void main(String[] args) {
    
    // 일반적으로 코드는 위에서 아래로 순서대로 실행한다.
    // 작업이 완료할 때까지 다음 줄로 가지 않는다.
    for (int i = 0; i < 1000; i++) {
      System.out.println("====> " + i);
    }
    
    for (int i = 0; i < 1000; i++) {
      System.out.println(">>>>> " + i);
    }

  }

}
