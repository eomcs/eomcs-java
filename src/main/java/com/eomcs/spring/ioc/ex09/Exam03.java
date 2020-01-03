// 객체 자동 생성 - 특정 패키지의 클래스에 대해 객체 생성하지 않기
package com.eomcs.spring.ioc.ex09;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step09/application-context-03.xml");

    BeanUtils.printBeanNames(iocContainer);
  }

}





