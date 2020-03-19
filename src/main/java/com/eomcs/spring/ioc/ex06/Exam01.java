// 팩토리 메서드 호출 - static 메서드 호출
package com.eomcs.spring.ioc.ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    Car c = CarFactory.create("티코");

    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step06/application-context-01.xml");

    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("c2"));
    System.out.println(iocContainer.getBean("c3"));
  }

}





