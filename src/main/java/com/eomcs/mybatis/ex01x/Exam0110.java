// Data Persistence Framework 도입 - Mybatis
package com.eomcs.mybatis.ex01x;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// Data Persistence Framework
// => 데이터의 영속성(지속성; 등록,조회,변경,삭제)을 대신 처리해주는 프레임워크.
// 1) SQL Mapper
// - 직접 SQL 문을 작성
// - 각각의 DBMS에 최적화된 SQL을 작성할 수 있다.
// - DBMS마다 미미하게 다른 SQL을 작성해야 하는 번거로움이 있다.
// - 예) Mybatis 등
// 2) OR Mapper
// - 전용언어 및 문법(Domain-Specific Language;DSL)을 사용하여 작성
// 실행할 때 DBMS에 맞춰서 SQL을 생성하여 실행한다.
// - DBMS에 마다 SQL문을 작성할 필요가 없어 편리하다.
// - DBMS에 최적화된 SQL을 실행할 수 없다.
// 즉 DBMS의 특징을 최대로 활용할 수 없다.
// - 예) Hibernate, TopLink 등
//
// Mybatis 도입
// 1) 의존 라이브러리 추가
// - mvnrepository.com 검색하여 build.gradle 파일에 추가한다.
// - "gradle eclipse" 실행한 후 이클립스 에디터에서 프로젝트를 "refresh" 한다.
// 2) mybatis 설정 파일 준비
// - mybatis-config.xml 생성 및 편집
// 3) DB 연결 정보를 담은 프로퍼티 파일 준비
// - jdbc.properties 생성 및 편집
// 4) SQL 문장을 작성할 파일 준비
// - BoardMapper.xml 생성 및 편집
// 5) Mybatis 객체 준비
//
public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // 1. mybatis 설정 파일을 읽을 InputStream 도구를 준비한다.
    // 1) 직접 파일 시스템 경로를 지정하기
    // => 단 소스 파일 경로를 지정하지 말아라.
    // => 컴파일된 후 XML 파일이 놓이는 경로를 지정해라.
    // => 자바 패키지에 작성한 일반 파일은 그대로 빌드 디렉토리에 복사된다.
    // => 예) 프로젝트폴더/bin/main/com/eomcs/mybatis/ex01/mybatis-config.xml
    //
    InputStream mybatisConfigInputStream = new FileInputStream(//
        "./bin/main/com/eomcs/mybatis/ex01/mybatis-config.xml");

    // 2. SqlSessionFactory를 만들어 줄 빌더 객체 준비
    //
    SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();

    // 3. SqlSession 객체를 만들어 줄 팩토리 객체 준비
    // => mybatis는 Builder를 이용하여 SqlSessionFactory 객체를 만든다.
    // => 이때 공장 객체를 만들 때 사용할 설정 파일을 지정한다.
    // => 설정 파일의 경로를 직접 지정하지 말고,
    // 해당 파일을 읽을 때 사용할 InputStream을 넘겨줘라.
    //
    SqlSessionFactory factory = factoryBuilder.build(mybatisConfigInputStream);

    // 4. SQL을 실행시키는 객체 준비
    // => SqlSessionFactory 객체로부터 SqlSession 객체를 얻는다.
    // => openSession()은 수동 커밋으로 SQL을 다루는 객체를 리턴한다.
    // => 자동 커밋으로 SQL을 다루고 싶다면,
    // openSession(boolean autoCommit) 메서드를 호출하라.
    //
    SqlSession sqlSession = factory.openSession();

    System.out.println("mybatis 준비 완료!");

    sqlSession.close();
  }

}


