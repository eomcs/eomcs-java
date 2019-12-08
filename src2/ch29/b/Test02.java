// 객체의 이름 설정
package ch29.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test02 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/b/application-context-02.xml");
    
    System.out.println("---------------------------------------");
    
    // 스프링 IoC 컨테이너에 보관된 객체 꺼내기
    String[] names = iocContainer.getBeanDefinitionNames();
    for (String name : names) {
      System.out.printf("%s ==> %s\n", 
          name, 
          iocContainer.getBean(name).getClass().getName());
      
      System.out.printf("    %s 객체의 별명: ", name);
      String[] aliases = iocContainer.getAliases(name);
      for (String alias : aliases) {
        System.out.print(alias + "      ");
      }
      System.out.println();
    }
    
    // 값을 꺼낼 때는 이름이든 별명이든 구분하지 않는다.
    Car c6 = (Car) iocContainer.getBean("c6");
    Car c7 = (Car) iocContainer.getBean("c7");
    Car c8 = (Car) iocContainer.getBean("c8");
    
    if (c6 == c7) System.out.println("c6 == c7");
    if (c6 == c8) System.out.println("c6 == c8");
    
    Car c9 = (Car) iocContainer.getBean("c9");
    System.out.println(c6 == c9);
  }
}






