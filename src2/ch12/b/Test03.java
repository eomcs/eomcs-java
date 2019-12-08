// getter/setter를 적용해야 하는 이유  
package ch12.b;

public class Test03 {

  public static void main(String[] args) {
    
    Score s1 = new Score();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    s1.compute();
    System.out.printf("총점=%d, 평균=%.1f\n", s1.getSum(), s1.getAver());
    
    // 이유1) 
    // 만약 국,영,수 점수를 변경한 후 compute()를 호출하지 않고 
    // 평균과 총점을 출력한다면?
    s1.kor = 70;
    
    // 분명 국어 점수를 변경했음에도 총점과 평균이 바뀌지 않는다.
    // 개발자가 compute()를 호출하는 것을 빠뜨리면 이런 문제가 발생한다.
    // 
    System.out.printf("총점=%d, 평균=%.1f\n", s1.getSum(), s1.getAver());
    
    // 이유2)
    // 만약 국, 영, 수 점수를 유효하지 않은 값으로 변경한다면?
    s1.eng = -200;
    
    // 제대로 계산까지 한다.
    s1.compute();
    
    // 이번에는 점수를 변경한 후 compute() 호출하여 제대로 계산까지 했지만,
    // 우리가 예상하는 결과와 다르게 나온다.
    // 이유는? 점수를 설정할 때 이미 유효하지 않은 값을 설정했기 때문이다.
    //
    System.out.printf("총점=%d, 평균=%.1f\n", s1.getSum(), s1.getAver());
    
    // 해결책?
    // => 점수를 직접 변경하게 하지 말고 메서드를 통해 변경하게 만들라!
    // => 1) 유효하지 않은 값은 값을 넣지 못하도록 막을 수 있다.
    // => 2) 점수를 변경한 후 자동으로 계산하게 할 수 있다.
    // Test04.java 를 확인하라!
  }

}






