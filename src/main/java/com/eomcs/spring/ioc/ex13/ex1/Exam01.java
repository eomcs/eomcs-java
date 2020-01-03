// AOP(Aspect-Oriented Programming) - AOP 필터 적용 전
package com.eomcs.spring.ioc.ex13.ex1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext(
            "bitcamp/java106/step13/ex1/application-context-01.xml");

    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.println(iocContainer.getBean(name).getClass().getName());
    }
    System.out.println("---------------------------------------");

    Caller caller = (Caller) iocContainer.getBean(Caller.class);
    caller.test();
  }

}



