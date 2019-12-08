// 트랜잭션 다루기 - commit
package ch25.d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Test02 {

  public static void main(String[] args) {
    
    
    try (
        Scanner keyboard = new Scanner(System.in);
        Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {

      // 1) 트랜잭션 시작 - 커넥션 객체의 오토커밋을 false로 지정한다.
      con.setAutoCommit(false);
      
      // 2) 데이터 변경 작업을 수행 - 여러 개의 insert, update, delete 작업 수행 
      while (true) {
        System.out.print("제목? ");
        String title = keyboard.nextLine();
        if (title.length() == 0)
          break;
        
        System.out.print("내용? ");
        String contents = keyboard.nextLine();
        
        try (PreparedStatement stmt = con.prepareStatement(
            "insert into x_board(title, contents) values(?,?)")) {
          
          stmt.setString(1, title);
          stmt.setString(2, contents);
          stmt.executeUpdate();
          System.out.println("입력 성공!");
        }
      }
      
      // 3) 트랜잭션을 종료 - 서버의 요청한 작업을 처리할 것을 명령한다.
      // => commit()을 호출하지 않으면 서버에 요청한 데이터 변경 작업은 자동 취소된다. 
      con.commit();
      
      // 주의!
      // => 트랜잭션은 커넥션 단위로 관리된다.
      // => 즉 같은 커넥션을 사용하면 서로 영향을 끼치게 된다. 

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}











