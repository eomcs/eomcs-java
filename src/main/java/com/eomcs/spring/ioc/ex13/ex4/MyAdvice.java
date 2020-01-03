// 특정 메서드 호출 전후에 실행되는 클래스
package com.eomcs.spring.ioc.ex13.ex4;

public class MyAdvice {

  // 타겟 객체의 메서드를 호출하기 전에 그 메서드가 받을 파라미터를 먼저 받기
  /*
        <aop:before 
            pointcut="execution(* bitcamp.java106.step13.ex4.X.*(..)) and args(a,b)" 
            method="doBefore"/>
   */
  public void doBefore(int a, int b) {
    System.out.printf("MyAdvice.doBefore(): %d, %d\n", a, b);
  }

  // 타겟 객체의 메서드를 호출한 후 그 결과를 받기
  // => 설정 파일에 정의된 이름을 사용하여 파라미터를 선언해야 한다.
  /*
    <aop:after-returning 
        pointcut="execution(* bitcamp.java106.step13.ex4.X.*(..))" 
        method="doAfterReturning"
        returning="returnValue"/>
   */
  public void doAfterReturning(Object returnValue) {
    System.out.printf("MyAdvice.doAfterReturning(): %d\n", returnValue);
  }

  // 타겟 객체의 메서드를 호출할 때 예외가 발생했다면 그 예외 객체를 받기
  // => 설정 파일에 정의된 이름을 사용하여 파라미터를 선언해야 한다.
  /*
    <aop:after-throwing 
        pointcut="execution(* bitcamp.java106.step13.ex4.X.*(..))" 
        method="doAfterThrowing"
        throwing="error"/>
   */
  public void doAfterThrowing(Exception error) {
    System.out.printf("MyAdvice.doAfterThrowing(): %s\n", error.getMessage());
  }
}










