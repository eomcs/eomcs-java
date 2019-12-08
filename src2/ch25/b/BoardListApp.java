// 연습 - 게시물 목록 출력
package ch25.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BoardListApp {

  // 다음과 같이 게시물 목록을 출력하라!
  // 게시물 번호를 내림차순으로 정렬하라.
  // ----------------------------
  // 번호, 제목, 등록일, 조회수
  // 2, aaa, 2019-1-1, 2
  // 1, bbb, 2018-12-31, 3
  //----------------------------
  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (Statement stmt = con.createStatement()) {
        
        try (ResultSet rs = stmt.executeQuery(
            "select * from x_board order by board_id desc")) {
        
          System.out.println("번호, 제목, 등록일, 조회수");
          while (rs.next()) {
            // 레코드에서 컬럼 값을 꺼낼 때 컬럼 번호를 지정하는 것 보다 
            // 컬럼의 이름을 지정하는 것이 유지보수에 더 좋다.
            //
            System.out.printf("%d, %s, %s, %s, %d\n", 
                rs.getInt("board_id"), 
                rs.getString("title"), 
                rs.getString("contents"), 
                rs.getDate("created_date"),
                rs.getInt("view_count"));
          }
        }
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
