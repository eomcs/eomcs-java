// JDBC 프로그래밍 - DBMS에 연결하기
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0210 {

  public static void main(String[] args) throws Exception {

    java.sql.Connection con = null;
    try {
      // DBMS에 연결하기
      // => DriverManager에게 DBMS와의 연결을 요청한다. 어느 서버에 접속할 것인지 정보를 제공해야 한다.
      // jdbc url : DBMS 서버 정보. 예) jdbc:mysql://서버주소:포트/데이터베이스명
      // (포트번호를 지정하지 않으면 기본이 3306 이다.)
      // username : DBMS 사용자 아이디
      // password : DBMS 사용자 암호
      //
      con = DriverManager.getConnection( //
          "jdbc:mysql://localhost:3306/studydb", // jdbcURL
          "study", // username
          "1111" // password
      );
      // => DriverManager는 등록된 java.sql.Driver 구현체 중에서 jdbc url에 지정된 객체를 찾는다.
      // 예) MariaDB: org.mariadb.jdbc.Driver 클래스의 객체
      // => DB 연결을 Driver 구현체에게 위임한다.
      // => Driver 구현체(org.mariadb.jdbc.Driver 객체)는 DBMS와 연결한 후
      // 소켓 정보를 갖고 있는 java.sql.Connection 구현체를 리턴한다.
      //
      System.out.println("DBMS와 연결됨!");

    } finally {
      // 자원해제
      // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
      con.close();
      System.out.println("DBMS와 연결 해제됨!");
    }
  }
}


