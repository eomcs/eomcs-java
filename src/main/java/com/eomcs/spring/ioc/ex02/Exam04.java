// 익명 객체
package com.eomcs.spring.ioc.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.SpringUtils;

public class Exam04 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step02/application-context-04.xml");

    SpringUtils.printBeanNames(iocContainer);

    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Car#0");
    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Car#1");
    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Car#2");
    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Car#3");

    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Engine#0");
    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Engine#1");
    SpringUtils.printBeanAliases(iocContainer, "bitcamp.java106.step02.Engine#2");

  }

}





