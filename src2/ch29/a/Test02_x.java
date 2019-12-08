// 빈 생성과 꺼내기
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test02_x {
  public static void main(String[] args) throws Exception {
    // 객체 생성
    // <bean id="빈의 이름" class="패키지명을 포함한 클래스명"/>
    //
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/a/application-context-02.xml");
    
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------");
    
    // 스프링 IoC 컨테이너가 생성한 객체 꺼내기
    Student b1 = (Student) iocContainer.getBean("b1");
    System.out.println(b1);
    
    // 존재하지 않는 객체를 꺼내려하면 예외가 발생한다.
    //Student b2 = (Student) iocContainer.getBean("b2");
    //System.out.println(b2);
    
    System.out.println("실행 완료!");
  }
}






