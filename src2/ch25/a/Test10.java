// java.sql.Statement - update 실행하기
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test10 {

  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");
      
      try (Statement stmt = con.createStatement()) {
        
        // update 문장은 executeUpdate()를 사용하여 서버에 전달한다.
        int count = stmt.executeUpdate(
            "update x_board set view_count = view_count + 20"
            + " where board_id > 1");
        // 리턴 값은 update 한 레코드의 개수이다.
        System.out.println(count);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}









