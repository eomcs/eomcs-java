// BeanPostProcessor - 생성자와 의존 객체
package ch29.h;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test09 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/h/application-context-09.xml");
    
    // 생성자의 파라미터에 의존 객체를 선언하면 
    // 스프링 IoC 컨테이너가 자동으로 주입해 준다.
    //
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("c1"));
  }
}






