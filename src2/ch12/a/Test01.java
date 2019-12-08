// 캐슐화(encapsulation) 필요성 - 사례 1
package ch12.a;

public class Test01 {

  public static void main(String[] args) {
    // 클래스를 만든 개발자의 의도에 따라 아주 잘 사용한 예:
    Calculator c = new Calculator();
    c.plus(100);
    c.plus(15);
    c.minus(27);
    System.out.println(c.result);
    
    // 클래스를 만든 개발자의 의도에 벗어나 사용한 예:
    Calculator c2 = new Calculator();
    c2.plus(100);
    c2.plus(15);
    
    // 임의로 result 변수의 값을 변경한다. 
    // => 정상적인 계산을 통해 결과 값이 설정되는 것이 아니라 
    //    이렇게 클래스를 사용하는 개발자가 임의로 값을 설정하게 되면 
    //    최종 계산 결과는 옳바르지 않을 수 있다.
    // => 문제는 현재 상태에서 이것을 막지 못한다는 것이다.
    //
    c2.result = 100;
    
    c2.minus(27);
    
    // 최종적으로 왜곡된 결과가 나온다.
    // => 개발자가 일부러 했든 아니면 실수로 했든 
    //    이렇게 중간에 결과를 저장하는 변수의 값을 조작하게 되면 
    //    언제든 왜곡된 결과가 나올 수 있다.
    System.out.println(c2.result);

  }

}









