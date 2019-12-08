// 빈 생성과 꺼내기
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.SpringUtils;

public class Test02_j {
  public static void main(String[] args) throws Exception {
    // 객체 생성
    // @Bean public Student b1() { return new Student(); }
    //
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig02.class);
    
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






