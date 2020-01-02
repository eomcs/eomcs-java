// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : update
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0350 {

  public static void main(String[] args) throws Exception {
    // 1) JDBC 드라이버 로딩 
    Class.forName("com.mysql.jdbc.Driver");
    System.out.println("JDBC 드라이버 로딩 및 등록 완료!");

    // 2) DBMS에 연결하기
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false",
        "java106", "1111");
    System.out.println("DBMS와 연결됨!");

    // 3) SQL문을 서버에 보낼 객체를 얻는다.
    //    - 리턴 값: SQL문을 DBMS의 형식에 따라 인코딩하여 서버에 전달하는 일을 하는 객체.
    java.sql.Statement stmt = con.createStatement();

    // 4) Statement 객체를 사용하여 DBMS에 SQL문을 보낸다.
    int count = stmt.executeUpdate(
        "update ex_board set titl='okok2' where bno=9");
    System.out.printf("%d 개 변경 성공!", count);


    // 자원해제
    // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
    stmt.close();
    con.close();
  }
}







