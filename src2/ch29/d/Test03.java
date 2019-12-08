// 프로퍼티 값 설정 - 의존 객체 주입 II
package ch29.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test03 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/d/application-context-03.xml");
    
    System.out.println("---------------------------------------");
    
    // 다른 객체의 프로퍼티에서 생성한 객체는 별도로 꺼낼 수 없다.
    //BlackBox bb1 = (BlackBox) iocContainer.getBean("bb1"); // 예외 발생!
    
    Car c1 = (Car) iocContainer.getBean("c1");
    //Car c2 = (Car) iocContainer.getBean("c2");
    Car c3 = (Car) iocContainer.getBean("c3");
    
    System.out.println(c1);
    //System.out.println(c2);   
    System.out.println(c3);
    
    // c1 프로퍼티에서 만든 BlackBox와 
    // c3 프로퍼티에서 만든 BlackBox는 서로 다른 객체이다.
    // 만약 c1과 c3가 같은 BlackBox를 공유하고 싶다면,
    // BlackBox 객체를 별도로 생성해야 한다. 즉 프로퍼티에서 생성하면 안된다.
    System.out.println(c1.getBlackBox() == c3.getBlackBox());
  }
}






