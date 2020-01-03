// 생성자 호출 
package com.eomcs.spring.ioc.ex03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step03/application-context-01.xml");

    BeanUtils.printBeanNames(iocContainer);
  }

}





