package ch30.c;

import org.springframework.stereotype.Component;

@Component
public class X {
  
  public void m1() {
    // 이 메서드에 기능을 추가하기 위해 별도로 코드를 작성할 필요가 없다.
    // 그래서 기능을 추가하고 제거할 때 아무런 영향을 끼치지 않는다.
    System.out.println("X.m1()");
  }
}
