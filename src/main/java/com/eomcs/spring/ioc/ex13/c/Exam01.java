// AOP(Aspect-Oriented Programming) - AOP 필터 적용 위치
package com.eomcs.spring.ioc.ex13.c;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex13/c/application-context.xml");

    SpringUtils.printBeanList(iocContainer);

    try {
      Caller caller = iocContainer.getBean(Caller.class);
      caller.test();
      // 실제 test()에서 호출하는 m1()은
      // X의 프록시 객체에 정의되어 있는 m1()을 호출하는 것이다.
      //
      // X의 프록시 클래스가 다음과 같이 정의되었을 것으로 짐작할 수 있다.
      // class XProxy extends X {
      // X original;
      // MyAdvice myAdvice;
      //
      // public Xproxy(X original, MyAdvice myAdvice) {
      // this.original = original;
      // this.myAdvice = myAdvice;
      // }
      //
      // public int m1(int a, int b) {
      // myAdvice.doBefore();
      // try {
      // try {
      // original.m1(a, b);
      // } catch (Exception e) {
      // throw e;
      // } finally {
      // myAdvice.doAfter();
      // }
      // myAdvice.doAfterReturning();
      // } catch (Exception e) {
      // myAdvice.doAfterThrowing();
      // }
      // }
      // }
      //
    } catch (Exception e) {
      System.err.println("오류 발생!");
    }
  }

}


