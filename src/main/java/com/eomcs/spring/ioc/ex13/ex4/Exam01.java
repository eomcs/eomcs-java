// AOP(Aspect-Oriented Programming) - AOP 필터에서 타겟 객체의 메서드를 호출하기 전에 파라미터를 받거나 리턴 값을 받기
package com.eomcs.spring.ioc.ex13.ex4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext(
            "bitcamp/java106/step13/ex4/application-context-01.xml");

    String[] names = iocContainer.getBeanDefinitionNames();
    System.out.println("---------------------------------------");
    for (String name : names) {
      System.out.println(iocContainer.getBean(name).getClass().getName());
    }
    System.out.println("---------------------------------------");

    try {
      Caller caller = (Caller) iocContainer.getBean(Caller.class);
      caller.test();
    } catch (Exception e) {
      System.err.println("오류 발생!");
    }
  }

}



