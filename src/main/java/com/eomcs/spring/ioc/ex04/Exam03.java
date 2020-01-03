// 프로퍼티 호출 - 객체 주입 시 객체 생성 순서
package com.eomcs.spring.ioc.ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step04/application-context-03.xml");

  }

}





