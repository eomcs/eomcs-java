package ch29.j;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Configuration
// => AnnotationConfigApplicationContext 컨테이너에 Java config 클래스를 직접 지정할 경우에는
//    굳이 @Configuration 애노테이션을 붙일 필요가 없다.
//    예) ApplicationContext iocContainer = 
//              new AnnotationConfigApplicationContext(AppConfig1.class);
// 
// => 그런데 다음과 같이 컨테이너에 Java config 클래스를 직접 알려주지 않을 경우에는,
//    예) ApplicationContext iocContainer = 
//              new AnnotationConfigApplicationContext("ch29.j");
//    이 클래스 Java config 클래스임을 표시해야만 컨테이너가 알 수 있다.
//    바로 Java config 클래스임을 표시할 때 붙이는 애노테이션이 @Configuration 이다.
//
@Configuration
@ComponentScan("ch29.j2")
public class AppConfig2 {

}
