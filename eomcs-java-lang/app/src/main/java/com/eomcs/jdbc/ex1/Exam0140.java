// JDBC 드라이버 준비 - DriverManager가 Driver 구현체를 자동 로딩
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0140 {

  public static void main(String[] args) {

    // JDBC 드라이버 등록 방법4: Driver 구현체 자동 로딩
    // => DriverManager를 사용할 때,
    //    DriverManager 는 다음 절차에 따라 Driver 구현체를 찾아서 자동으로 로딩한다.
    //
    // 1) jdbc.drivers 시스템 프로퍼티에 지정된 구현체를 찾아 로딩한다.
    // => jdbc.drivers=foo.bah.Driver:wombat.sql.Driver:bad.taste.ourDriver
    // => 이때 각 Driver 구현체는 'system class loader'를 통해 로딩된다.
    // => 시스템 프로퍼티? JVM에 설정된 "key=value" 이다.
    // => 시스템 프로퍼티를 꺼내는 방법? 다음과 같다.
    System.out.printf("java.home=%s\n", System.getProperty("java.home"));
    System.out.printf("user.home=%s\n", System.getProperty("user.home"));
    System.out.printf("jdbc.drivers=%s\n", System.getProperty("jdbc.drivers"));

    // => 시스템 프로퍼티 설정 방법
    // 예1) JVM을 실행할 때 JVM 옵션을 지정하는 방법
    // - java -Djdbc.drivers=클래스명:클래스명:클래스명 Exam0140
    //
    // 예2) 프로그램 코드에서 설정하는 방법
    // - System.setProperty("jdbc.drivers", "com.eomcs.jdbc.ex1.MyDriver");
    //
    //    System.setProperty("jdbc.drivers", "com.eomcs.jdbc.ex1.MyDriver");
    //    System.out.printf("jdbc.drivers=%s\n", System.getProperty("jdbc.drivers"));

    try {
      // Driver 구현체를 로딩하지 않는다!

      // DriverManager에 자동 등록된 것을 확인해보자!
      // => DriverManager의 getDriver()를 호출하여 java.sql.Driver 구현체를 달라고 요구할 때,
      //    1) JDBC 드라이버 초기화 절차를 수행하지 않았으면,
      //       - CLASSPATH 경로에 설정된 모든 *.jar 파일에 대해 
      //         서비스 로더 규칙(META-INF/services/java.sql.Driver 파일)에 따라 Driver 구현체를 찾아 로딩한다.
      //       - JVM 프로퍼티 "jdbc.drivers" 이름으로 등록된 Driver 구현체가 있다면 그 클래스를 로딩한다. 
      //    2) 등록된 Driver 구현체 목록에서 해당 드라이버를 찾아 리턴한다.
      //    
      java.sql.Driver driver = DriverManager.getDriver("jdbc:mariadb://");
      System.out.println(driver);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


