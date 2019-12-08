package ch29.k1;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스는 스프링 IoC 컨테이너를 설정하는 클래스임을 표시한다.
@Configuration

// Mybatis와 스프링 IoC 컨테이너를 연동하는 설정
// => Mybatis 관련 빈 설정

// Mybatis DAO 구현체 자동 생성하기
// => @MapperScan(DAO 인터페이스가 들어 있는 패키지)
// => DAO 인터페이스를 구현한 객체를 자동으로 생성하여 IoC 컨테이너에 보관한다. 
// => DAO 객체의 메서드를 호출할 때 인터페이스 이름과 메서드명을 합쳐서 
//    SQL 문을 Mapper 파일에서 찾는다.
@MapperScan("ch29.k1.dao")

public class MybatisConfig {
  
  // Mybatis의 SqlSessionFactory 객체 준비
  @Bean
  public SqlSessionFactory sqlSessionFactory(
      DataSource dataSource, ApplicationContext appCtx) throws Exception {
    
    // SqlSessionFactory를 만들 때 기존의 SqlSessionFactoryBuilder를 사용하면,
    // Mybatis 설정 파일(예:mybatis-config.xml)이 필요하다.
    // 이런 설정 파일없이 스프링 IoC 컨테이너에서 SqlSessionFactory를 만들 수 있도록 
    // 도와주는 클래스가 SqlSessionFactoryBean 이다.
    // 이 클래스는 mybatis-spring 라이브러리에 들어 있기 때문에, 별도의 다운로드가 필요하다.
    //
    // SqlSessionFactoryBean 클래스는 FactoryBean 인터페이스를 구현한 클래스이다.
    // 보통 FactoryBean 구현체의 이름을 정의할 때는 
    // "생성하는 객체의 클래스명 + FactoryBean" 이름으로 짓는다. 예) CarFactoryBean
    // 그런데 Mybatis-spring 라이브러리에서는 
    // SqlSessionFactoryFactoryBean 이라는 이름으로 짓지 않고 
    // 중간에 Factory가 두 번 들어가서 보기 싫다고 한 개를 빼버렸다. 
    // 그래서 SqlSessionFactoryBean 이라는 이름이 되었다.
    // 주의하라! 
    // 기존의 이름 관행에 따라 SqlSessionFactoryBean이 SqlSession 객체를 생성한다고 생각하기 쉬운데, 
    // 아니다! SqlSessionFactoryBean 은 SqlSessionFactory 객체를 생성해준다.
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    
    // DataSource를 주입한다.
    sqlSessionFactoryBean.setDataSource(dataSource);
    
    // SQL 매퍼 파일에서 사용할 클래스에 대한 별명을 지정하기
    // => 별명을 자동으로 생성하고 싶은 클래스들이 들어 있는 패키지를 지정한다.
    sqlSessionFactoryBean.setTypeAliasesPackage("ch29.k1.vo");
    
    // SQL 매퍼 파일이 위치를 지정하기
    // => SQL Mapper 파일의 경로는 Resource 객체 배열에 담아 넘긴다.
    // => Resource 객체는 스프링 IoC 컨테이너를 통해 얻을 수 있다.
    sqlSessionFactoryBean.setMapperLocations(
        appCtx.getResources("classpath:ch29/k1/mapper/*.xml"));
    
    return sqlSessionFactoryBean.getObject();
  }
}






