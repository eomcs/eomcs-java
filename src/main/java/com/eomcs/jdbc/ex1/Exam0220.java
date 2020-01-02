// JDBC 프로그래밍 - DBMS에 연결하기
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0220 {

  public static void main(String[] args) throws Exception {
    // 1) JDBC 드라이버 로딩 
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("JDBC 드라이버 로딩 및 등록 완료!");

    // 2) DBMS에 연결하기
    // => DriverManager를 통해 등록된 Driver 객체를 사용하여 DBMS와 연결한다.
    //    jdbc url : DBMS 서버 정보. 예) jdbc:mysql://서버:포트/데이터베이스명
    //    username : DBMS 사용자 아이디
    //    password : DBMS 사용자 암호
    // => 리턴 : Driver가 DBMS와 연결한 후 연결 정보를 다루는 객체를 리턴한다.
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false&user=java106&password=1111");
    // JDBC URL에 사용자 아이디와 암호를 포함할 수 있다.


    System.out.println("DBMS와 연결됨!");

    // 자원해제
    // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
    con.close();
  }
}







