// Java config - 자바 클래스로 스프링 IoC 컨테이너 설정하기
package ch29.j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ch29.SpringUtils;

public class Test01 {
  public static void main(String[] args) {
    
    // java config를 다룰 때는 AnnotationConfigApplicationContext 클래스를 사용한다.
    ApplicationContext iocContainer = 
        new AnnotationConfigApplicationContext(AppConfig1.class);
    
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
  }
}






