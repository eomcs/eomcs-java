// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : insert
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0310 {

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
    //    - executeUpdate(INSERT/UPDATE/DELETE 등 DML 관련 SQL문)
    //      => 리턴값: 변경(insert/update/delete)된 데이터의 개수
    //    - executeQuery(SELECT 등 DQL 관련 SQL문)
    //      => 리턴값: 서버에서 데이터를 가져올 때 사용하는 도구(객체)
    // 용어정리
    // DML(Data Manipulation Language)
    // - insert, update, delete 처럼 데이터를 조작하는 sql 명령을 말한다.
    // DQL(Data Query Language)
    // - select처럼 data를 조회하는 sql 명령을 말한다.
    // 
    int count = stmt.executeUpdate(
        "insert into ex_board(titl,cont,rdt) values('aaa','xxxx',now())");
    System.out.printf("%d 개 입력 성공!", count);


    // 자원해제
    // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
    stmt.close();
    con.close();
  }
}







