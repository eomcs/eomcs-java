// 스프링 IoC 컨테이너 사용
package com.eomcs.spring.ioc.ex01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Exam03 {

  public static void main(String[] args) {
    // 스프링 IoC 컨테이너는 ApplicationContext를 구현한다.
    ApplicationContext iocContainer = null;

    // ApplicationContext 구현체 종류
    // 1) XML 파일에 작성된 설정 정보를 읽어서 처리하는 IoC 컨테이너
    //    => ClassPathXmlApplicationContext (자바 classpath에서 xml 파일을 찾는다)
    //    => FileSystemXmlApplicationContext (OS 파일시스템에서 xml 파일을 찾는다)
    // 2) 클래스 안에 작성된 애노테이션을 읽어서 처리하는 IoC 컨테이너
    //    => AnnotationConfigApplicationContext
    // 


    // AnnotationConfigApplicationContext 사용
    // => 스프링 설정정보를 담고 있는 클래스 정보를 넘긴다.
    iocContainer = new AnnotationConfigApplicationContext(AppConfig.class); 

    // IoC 컨테이너에 보관된 객체를 꺼내기
    Car car = (Car) iocContainer.getBean("c1");
    System.out.println(car);

  }

}
