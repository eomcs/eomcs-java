// 의존 객체 주입 자동화하기 - BeanPostProcessor 응용
package com.eomcs.spring.ioc.ex08.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.eomcs.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    // Spring IoC 컨테이너가 생성한 객체의 클래스 명을 로그로 남기고 싶다.
    // => LogBeanPostProcessor 를 만들어 처리한다.
    //
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "com/eomcs/spring/ioc/ex08/d/application-context.xml");

    // Spring IoC 컨테이너에 들어 있는 객체 출력
    SpringUtils.printBeanList(iocContainer);
  }

}


