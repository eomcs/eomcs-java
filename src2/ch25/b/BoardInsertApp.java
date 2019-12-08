// 연습 - 게시물 등록
package ch25.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BoardInsertApp {

  // 다음과 같이 게시물을 등록하는 프로그램을 작성하라!
  // ----------------------------
  // 제목? aaa
  // 내용? bbb
  // 등록하시겠습니까?(Y/n) y
  // 등록하였습니다.
  // 등록하시겠습니까?(Y/n) n
  // 등록을 취소 하였습니다.
  //----------------------------
  public static void main(String[] args) {
    String title = null;
    String contents = null;

    try (Scanner keyboard = new Scanner(System.in)) {
      System.out.print("제목? ");
      title = keyboard.nextLine();
      
      System.out.print("내용? ");
      contents = keyboard.nextLine();
      
      System.out.print("입력하시겠습니까?(Y/n) ");
      String input = keyboard.nextLine();
      
      if (!input.equalsIgnoreCase("y") &&
          input.length() != 0) {
        System.out.println("등록을 취소 하였습니다.");
        return;
      }
    }
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      
      try (Statement stmt = con.createStatement()) {
        
        stmt.executeUpdate(
            "insert into x_board(title,contents)"
            + " values('" + title + "','" + contents + "')");
        
        System.out.println("등록하였습니다.");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
