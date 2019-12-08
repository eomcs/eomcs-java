package ch29.a;

import org.springframework.context.annotation.Bean;

public class AppConfig02 {
  
  // 스프링 IoC 컨테이너에게 이 메서드를 호출하여 그 리턴 값을 
  // 빈 컨테이너에 보관하도록 요청한다.
  // 빈을 보관할 때 메서드 이름을 빈의 이름으로 사용한다.
  @Bean public Student b1() {
    return new Student();
  }
  
}
