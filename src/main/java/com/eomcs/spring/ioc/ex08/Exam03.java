// 의존 객체 주입 자동화하기 - BeanPostProcessor 동작 원리
package com.eomcs.spring.ioc.ex08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam03 {

  public static void main(String[] args) {
    //    
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step08/application-context-03.xml");

    System.out.println(iocContainer.getBean("c1"));
  }

}





