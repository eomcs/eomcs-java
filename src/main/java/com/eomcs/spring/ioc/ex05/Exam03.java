// 프로퍼티 호출 
package com.eomcs.spring.ioc.ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step05/application-context-03.xml");

    System.out.println(iocContainer.getBean("c1"));
  }

}





