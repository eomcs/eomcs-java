// @Component - 스프링 IoC 컨테이너가 관리할 객체에 붙이는 애노테이션
package ch29.i;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/i/application-context-01.xml");
    
    // @Component 애노테이션 붙은 클래스의 인스턴스를 자동으로 생성한다.
    //
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("car"));
  }
}






