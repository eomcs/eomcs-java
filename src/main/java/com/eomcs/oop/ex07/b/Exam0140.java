// 캡슐화(encapsulation) - 셋터와 겟터 
package com.eomcs.oop.ex07.b;

public class Exam0140 {

  public static void main(String[] args) {
    // 환자 데이터를 등록해보자!
    Customer4 c1 = new Customer4();

    // 실무에서 만드는 셋터는 보통 파라미터 값을 검증하지 않기 때문에 
    // 그냥 입력된 값 그대로 인스턴스 변수에 저장한다.
    // 그래서 값을 꺼내 출력해보면 입력된 값 그대로 출력될 것이다.
    c1.setName("홍길동");
    c1.setAge(300);
    c1.setWeight(100);
    c1.setHeight(-50);

    System.out.printf("%s, %d, %d, %d\n", 
        c1.getName(), c1.getAge(), c1.getWeight(), c1.getHeight());
  }
}







