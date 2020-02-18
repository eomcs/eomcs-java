// JDBC 드라이버 준비 - DriverManager가 Driver 구현체를 자동 로딩
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0140 {

  public static void main(String[] args) {

    // JDBC 드라이버 로딩 방법4: Driver 구현체 자동 로딩
    // => DriverManager를 사용할 때,
    // DriverManager 는 다음 절차에 따라 Driver 구현체를 자동 로딩한다.
    //
    // 1) jdbc.drivers 시스템 프로퍼티에 지정된 구현체를 찾아 로딩한다.
    // => jdbc.drivers=foo.bah.Driver:wombat.sql.Driver:bad.taste.ourDriver
    // => 이때 각 Driver 구현체는 'system class loader'를 통해 로딩된다.
    //
    // 2) java.sql.Driver 클래스의 서비스 제공자를 찾아 로딩한다.
    // => jar 파일 안에 META-INF/services/java.sql.Driver 파일을 찾는다.
    // => 이때 'service-provider loading' 절차에 따라 이 파일에 등록된 클래스를 로딩한다.
    // => jar 파일에 해당 정보가 있다면,
    // 앞의 예제처럼 개발자가 따로 java.sql.Driver 구현체를 명시적으로 등록하지 않아도 된다.
    // => mariadb JDBC 드라이버 jar 파일은 이 정보가 들어 있다.
    // 따라서 java.sql.Driver를 구현한 클래스를 로딩하거나 생성할 필요가 없다.
    //
    try {

      // DriverManager에 자동 등록된 것을 확인해보자!
      java.sql.Driver driver = DriverManager.getDriver("jdbc:mariadb:");
      System.out.println("JDBC 드라이버 로딩 및 등록 완료!");

    } catch (Exception e) {
      System.out.println("DBMS에 연결 중 오류 발생!");
      e.printStackTrace();
    }
  }
}


