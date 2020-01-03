// 프로퍼티 에디터 - String ==> java.sql.Date ; 변환기 없이 처리하기
package com.eomcs.spring.ioc.ex07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam02 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step07/application-context-02.xml");

    System.out.println(iocContainer.getBean("c1"));
  }

}





