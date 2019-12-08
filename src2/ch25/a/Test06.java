// java.sql.Statement 객체 - insert SQL을 DBMS 서버에 전달한다. 
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test06 {

  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");
      
      // Connection 객체를 통해 SQL을 DBMS에 전달할 때 사용할 도구를 얻는다.
      // => java.sql.Statement 구현체이다.
      try (Statement stmt = con.createStatement()) {
        
        // Statement 구현체를 이용하여 SQL문을 서버에 전달한다.
        // executeUpdate() : insert, update, delete 등 DDL, DMS 문법을 전달한다.
        // executeQuery() : select 문법을 전달한다.
        // execute() : 모든 SQL문을 전달한다.
        // executeBatch() : 여러 개의 SQL을 전달한다.
        // 
        int count = stmt.executeUpdate("insert into x_board(title,contents)"
            + " values('제목10','내용')");
        
        // executeUpdate()의 리턴 값은 SQL을 실행한 후 입력, 변경, 삭제된 
        // row(=record)의 개수이다.
        System.out.println(count);
        
        System.out.println("입력 성공!");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}









