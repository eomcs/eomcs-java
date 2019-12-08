package ch29.k1;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

//이 클래스는 스프링 IoC 컨테이너를 설정하는 클래스임을 표시한다.
@Configuration

// Mybatis와 스프링 IoC 컨테이너를 연동하는 설정
// => Database 관련 빈 설정

// jdbc.properties 파일을 로딩
@PropertySource("classpath:ch29/k1/jdbc.properties")
public class DatabaseConfig {
  
  @Value("${jdbc.driver}") String jdbcDriver;

  @Value("${jdbc.url}") String jdbcUrl;
  
  @Value("${jdbc.username}") String jdbcUsername;
  
  @Value("${jdbc.password}") String jdbcPassword;
  
  // DB 커넥션풀 객체 준비
  @Bean
  public DataSource dataSource() {
    System.out.println("dataSource()");
    // spring-jdbc 라이브러리에서 제공해주는 클래스를 사용하여 DataSource 구현체 만들기
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(this.jdbcDriver); 
    ds.setUrl(this.jdbcUrl);
    ds.setUsername(this.jdbcUsername);
    ds.setPassword(this.jdbcPassword);
    return ds;
  }
  
  // 트랜잭션 관리자 준비
  // => Spring IoC 컨테이너에서 트랜잭션 관리자를 찾을 때 
  //    "transactionManager"라는 이름으로 찾는다.
  // => 따라서 가능한 트랜잭션 관리자 이름을 이에 맞춰 "transactionManager"로 지어라.
  // => 만약 다른 이름으로 짓는다면 트랜잭션과 관련된 객체를 다룰 때
  //    매번 트랜잭션 관리자 이름을 지정해야 하는 번거로움이 발생한다.
  //    
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    System.out.println("transactionManager()");
    // 트랜잭션 관리자를 생성할 때 DataSource(DB 커넥션풀) 구현체를 요구한다.
    // Spring IoC 컨테이너에 들어 있는 것을 꺼내기 위해 
    // 파라미터로 선언하라.
    return new DataSourceTransactionManager(dataSource);
  }
}






