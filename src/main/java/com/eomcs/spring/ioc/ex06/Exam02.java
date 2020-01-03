// 팩토리 메서드 호출 - static 메서드 호출 응용 
package com.eomcs.spring.ioc.ex06;

import java.sql.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam02 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step06/application-context-02.xml");

    Date date = (Date) iocContainer.getBean("d1");
    System.out.println(date);
  }

}





