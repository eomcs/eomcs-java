// 객체 생성 정책 - singleton, prototype
package com.eomcs.spring.ioc.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.eomcs.spring.ioc.BeanUtils;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "bitcamp/java106/step02/application-context-03.xml");

    BeanUtils.printBeanNames(iocContainer);

    // scope=singleton : 객체를 한 개만 생성해서 공유한다. 
    Car obj1 = (Car) iocContainer.getBean("c1");
    Car obj2 = (Car) iocContainer.getBean("c1");
    if (obj1 == obj2) System.out.println("obj1 == obj2");

    Car obj3 = (Car) iocContainer.getBean("c2");
    Car obj4 = (Car) iocContainer.getBean("c2");
    if (obj3 == obj4) System.out.println("obj3 == obj4");

    // prototype 객체는 getBean()을 호출할 때마다 생성된다.
    Car obj5 = (Car) iocContainer.getBean("c3");
    Car obj6 = (Car) iocContainer.getBean("c3");
    if (obj5 != obj6) System.out.println("obj5 != obj6");
  }

}





