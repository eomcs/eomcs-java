// 객체 자동 생성 - @Component 애노테이션
package com.eomcs.spring.ioc.ex09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step09/application-context-01.xml");

    BeanUtils.printBeanNames(iocContainer);

    System.out.println(iocContainer.getBean("car"));
  }

}





