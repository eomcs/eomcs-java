// 프로퍼티 호출 - 객체 주입
package com.eomcs.spring.ioc.ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step04/application-context-02.xml");

    System.out.println(iocContainer.getBean("c1"));
    System.out.println(iocContainer.getBean("c2"));
  }

}





