package ch29.k1;

import org.springframework.context.annotation.ComponentScan;

// Mybatis와 스프링 IoC 컨테이너를 연동하는 설정
// => 애플리케이션 관련 빈 설정

// 스프링 IoC 컨테이너에 다른 설정이 들어 있는 Java config 파일이 있는 위치를 알려준다.
@ComponentScan("ch29.k1")

public class AppConfig {
  
}






