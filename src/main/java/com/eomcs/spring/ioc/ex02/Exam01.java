// 객체 생성 
package com.eomcs.spring.ioc.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step02/application-context-01.xml");

    BeanUtils.printBeanList(iocContainer);
  }

}





