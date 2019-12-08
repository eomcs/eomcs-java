// 캐슐화(encapsulation) 필요성 - 사례 2
package ch12.a;

public class Test02 {

  public static void main(String[] args) {
    // 클래스를 만든 개발자의 의도에 따라 아주 잘 사용한 예:
    Score s1 = new Score();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    s1.compute();
    System.out.printf("총점=%d, 평균=%.1f\n", s1.sum, s1.aver);
    
    // 클래스를 만든 개발자의 의도에 벗어나 사용한 예:
    Score s2 = new Score();
    s2.name = "홍길동";
    s2.kor = 100;
    s2.eng = 90;
    s2.math = 80;
    s2.compute();
    
    // 개발자가 aver 값을 조작하더라도 막을 수 없다.
    // 일부로 조작하든 실수로 조작하든 막을 수 없다.
    s2.aver = 95.5f;
    
    System.out.printf("총점=%d, 평균=%.1f\n", s2.sum, s2.aver);

  }

}









