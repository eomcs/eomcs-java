// 팩토리 메서드 호출 - FactoryBean 구현체 
package com.eomcs.spring.ioc.ex06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam05 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step06/application-context-05.xml");

    // 이 예제는 Exam04 예제와 같다.
    // 다만 Factory 클래스의 이름을 CarFactory3에서 CarFactoryBean으로 바꾼 것이다.
    System.out.println(iocContainer.getBean("c1"));
  }

}





