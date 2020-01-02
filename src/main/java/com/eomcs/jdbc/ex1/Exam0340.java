// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : select
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0340 {

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
    java.sql.Statement stmt = con.createStatement();

    // 4) Statement 객체를 사용하여 DBMS에 SQL문을 보낸다.
    java.sql.ResultSet rs = stmt.executeQuery(
        "select bno,titl,cont,rdt from ex_board");

    // 5) ResultSet 객체를 사용하여 서버에서 select의 결과 한 레코드(row)를 가져온다.
    while (rs.next()) {
      System.out.printf("%d,%s,%s,%s\n", 
          rs.getInt("bno"),  
          rs.getString("titl"),
          rs.getString("cont"),
          rs.getDate("rdt")/* java.sql.Date 객체 리턴 */); 
    }

    // 자원해제
    // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
    rs.close();
    stmt.close();
    con.close();
  }
}







