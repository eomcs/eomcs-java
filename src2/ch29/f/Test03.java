// 팩토리 클래스를 통해 객체 생성하기 III - 스프링 IoC 컨테이너의 FactoryBean 규칙에 따라 공장 클래스 만들기
package ch29.f;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/f/application-context-03.xml");
    
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    /*
    System.out.println(iocContainer.getBean("blackBox")); // BlackBox 객체 리턴
    System.out.println(iocContainer.getBean("carFactory"));// CarFactor2 객체 리턴
    System.out.println(iocContainer.getBean("c1")); // Car 객체 리턴 
    System.out.println(iocContainer.getBean("c1")); // 기존에 생성된 Car 객체 리턴
    */
  }
}






