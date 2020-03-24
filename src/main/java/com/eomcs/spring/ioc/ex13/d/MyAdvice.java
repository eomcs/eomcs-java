// 특정 메서드 호출 전후에 실행되는 클래스
package com.eomcs.spring.ioc.ex13.d;

public class MyAdvice {

  // 타겟 객체의 메서드를 호출하기 전에 
  // 그 메서드가 받을 파라미터를 먼저 받기
  /*
        <aop:before 
            pointcut="execution(* com.eomcs.spring.ioc.ex13.d.X.*(..)) and args(y,x)" 
            method="doBefore"/>
   */
  public void doBefore(int x, int y) {
    // 타겟 메서드의 파라미터 값을 받을 파라미터를 선언한다. 
    // AOP 설정할 때 파라미터 값을 받을 변수 이름을 지정한다.
    // 위 규칙에 따르면,
    // 타겟 메서드의 첫 번째 파라미터 값은 y 변수에서 받을 것이고
    // 두 번째 파라미터 값은 x 변수에서 받을 것이다.
    //
    System.out.printf("MyAdvice.doBefore(): %d, %d\n", x, y);
  }

  // 타겟 객체의 메서드를 호출한 후 그 결과를 받기
  // => 설정 파일에 정의된 이름을 사용하여 파라미터를 선언해야 한다.
  /*
    <aop:after-returning 
        pointcut="execution(* com.eomcs.spring.ioc.ex13.d.X.*(..))" 
        method="doAfterReturning"
        returning="returnValue"/>
   */
  public void doAfterReturning(Object returnValue) {
    // 타겟 메서드의 리턴 값은 
    // 설정 파일에 정의한 대로 
    // 'returnValue' 라는 이름의 변수에서 받을 것이다.
    //
    System.out.printf("MyAdvice.doAfterReturning(): %d\n", returnValue);
  }

  // 타겟 객체의 메서드를 호출할 때 예외가 발생했다면 그 예외 객체를 받기
  // => 설정 파일에 정의된 이름을 사용하여 파라미터를 선언해야 한다.
  /*
    <aop:after-throwing 
        pointcut="execution(* com.eomcs.spring.ioc.ex13.d.X.*(..))" 
        method="doAfterThrowing"
        throwing="error"/>
   */
  public void doAfterThrowing(Exception error) {
    // 타겟 메서드를 실행하다가 오류가 발생하면,
    // 설정 파일에서 지정한 대로 
    // 'error'라는 이름의 변수로 오류 정보를 받을 것이다.
    //
    System.out.printf("MyAdvice.doAfterThrowing(): %s\n", error.getMessage());
  }
}










