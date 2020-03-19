// 객체 생성 
package com.eomcs.spring.ioc.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.SpringUtils;

public class Exam02 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step02/application-context-02.xml");

    SpringUtils.printBeanList(iocContainer);

    // 빈의 아이디로 객체를 꺼내기
    Car c1 = (Car) iocContainer.getBean("c1");
    Car c2 = (Car) iocContainer.getBean("c2");

    if (c1 != c2) System.out.println("c1 != c2");

    // 빈의 별명이 지정된 경우 별명으로도 객체를 꺼낼 수 있다.
    Car c3 = (Car) iocContainer.getBean("c3");
    if (c2 == c3) System.out.println("c2 == c3");

    System.out.println("----------------------------");
    SpringUtils.printBeanAliases(iocContainer, "c1");
    SpringUtils.printBeanAliases(iocContainer, "c2");
    SpringUtils.printBeanAliases(iocContainer, "c4");
    SpringUtils.printBeanAliases(iocContainer, "c5");
    SpringUtils.printBeanAliases(iocContainer, "c6");
    SpringUtils.printBeanAliases(iocContainer, "c7");
    SpringUtils.printBeanAliases(iocContainer, "c8");
    SpringUtils.printBeanAliases(iocContainer, "c91");
  }

}





