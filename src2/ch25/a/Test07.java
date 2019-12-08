// java.sql.ResultSet 객체 - 서버에서 select의 결과를 가져오기 
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test07 {

  public static void main(String[] args) {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      System.out.println("DBMS에 연결됨!");
      
      try (Statement stmt = con.createStatement()) {
        
        // executeQuery()의 리턴 값은 ResultSet 구현체 이다.
        // ResultSet 객체?
        // => 서버의 select 실행 결과를 가져올 때 사용하는 도구이다.
        // => 단 한 개씩 가져온다. 한 번에 모두 가져오는 것이 아니다.
        //
        try (ResultSet rs = stmt.executeQuery(
            "select * from x_board order by board_id desc")) {
        
          // next() 
          // - 서버에서 한 개의 row(record)를 가져온다.
          // - 만약 가져올 레코드가 없으면 false를 리턴한다. 
          while (rs.next()) {
            // 서버에서 받아 온 레코드에서 컬럼 값 꺼내기
            // => getXxx(컬럼명) 또는 getXxx(컬럼 번호)
            // => 정수값 컬럼 : getInt(), varchar/char/text 문자열 컬럼: getString()
            // => select 문자에서 컬럼의 이름을 지정하는 대신에 *를 사용한다면,
            //    컬럼의 순서는 테이블을 생성할 때 지정한 컬럼 순서와 같다.
            // => 레코드에서 컬럼값을 꺼낼 때 컬럼의 번호는 1부터 시작한다.
            //
            System.out.printf("%d, %s, %s, %s, %d\n", 
                rs.getInt(1), // board_id
                rs.getString(2), // title
                rs.getString(3), // contents
                rs.getDate(4), // created_date
                rs.getInt(5)); // view_count
          }
        }
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}









