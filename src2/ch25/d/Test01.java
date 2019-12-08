// insert 한 후 auto increment PK 값 리턴 받기 
package ch25.d;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test01 {

  public static void main(String[] args) {
    String title = null;
    String contents = null;

    try (Scanner keyboard = new Scanner(System.in)) {
      System.out.print("제목? ");
      title = keyboard.nextLine();
      
      System.out.print("내용? ");
      contents = keyboard.nextLine();
    }
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      // 입력 후 PK 값을 리턴 받고 싶다면,
      // PreparedStatement 객체를 얻을 때 다음과 같은 옵션을 지정하라!
      // => prepareStatement(sql, 자동생성된 PK 값 리턴 여부)
      try (PreparedStatement stmt = con.prepareStatement(
          "insert into x_board(title, contents) values(?,?)", 
          Statement.RETURN_GENERATED_KEYS)) {
        
        stmt.setString(1, title);
        stmt.setString(2, contents);
        stmt.executeUpdate();
        
        // insert 수행 후 자동 생성된 PK 값을 요구해야 한다.
        int no = 0;
        try (ResultSet rs = stmt.getGeneratedKeys()) {
          // insert를 한 개만 했기 때문에 PK도 한 개만 생성되었다.
          // 따라서 ResultSet에 대해 여러 번 반복을 할 필요가 없다.
          rs.next();
          
          // 자동 생성된 PK 값을 꺼낼 때는 따로 컬럼 이름이 없기 때문에 컬럼 인덱스로 꺼낸다.
          no = rs.getInt(1);
        }
        
        System.out.println("입력 성공!");
        System.out.println("입력된 게시물 번호: " + no);
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}











