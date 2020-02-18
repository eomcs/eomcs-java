// JDBC 드라이버 준비 - Driver 객체 생성 및 자동 등록
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Exam0120 {

  public static void main(String[] args) throws Exception {

    // JDBC 드라이버 로딩 방법2: Driver 구현 객체 생성과 자동 등록
    // => Driver 객체를 생성하면 자동으로 DriverManager에 등록된다.
    // => 실제는 Driver 구현 클래스를 로딩하는 과정에서 자동 등록된다.

    try {
      new org.mariadb.jdbc.Driver();

      // DriverManager에 자동 등록된 것을 확인해보자!
      java.sql.Driver driver = DriverManager.getDriver("jdbc:mariadb:");
      System.out.println("JDBC 드라이버 로딩 및 등록 완료!");
      // 해당 드라이버가 등록되지 않았으면 예외가 발생할 것이다.

      // 등록되지 않은 드라이버를 요구해봐라! 예외가 발생할 것이다.
      // driver = DriverManager.getDriver("jdbc:oracle:"); // 예외 발생!

    } catch (SQLException e) {
      System.out.println("MariaDB의 java.sql.Driver 구현체를 등록하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }
}
