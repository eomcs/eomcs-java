// 트랜잭션 다루기 - autocommit = true 일 경우의 문제
package com.eomcs.jdbc.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Exam0510 {

  public static void main(String[] args) throws Exception {
    String title = null;
    String contents = null;
    String filename = null;

    try (Scanner keyScan = new Scanner(System.in)) {

      // 사용자로부터 제목, 내용을 입력 받는다.
      System.out.print("제목? ");
      title = keyScan.nextLine();

      System.out.print("내용? ");
      contents = keyScan.nextLine();

      System.out.print("첨부파일명? ");
      filename = keyScan.nextLine();

      System.out.print("입력하시겠습니까?(Y/n) ");
      String input = keyScan.nextLine();

      if (!input.equalsIgnoreCase("y") && input.length() != 0) {
        System.out.println("등록을 취소 하였습니다.");
        return;
      }
    }

    try (Connection con = DriverManager.getConnection( //
        "jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        PreparedStatement stmt = con.prepareStatement( //
            "insert into x_board(title,contents) values(?,?)", //
            Statement.RETURN_GENERATED_KEYS);) {

      // DriverManager가 리턴한 커넥션은 Auto Commit의 기본 상태가 true로 설정되어 있다.
      // Auto Commit?
      // => insert, update, delete을 실행한 후 그 결과를 즉시 테이블에 적용하는 것.

      stmt.setString(1, title);
      stmt.setString(2, contents);
      int count = stmt.executeUpdate();
      System.out.printf("%d 개 입력 성공!\n", count);

      int no = 0;
      try (ResultSet rs = stmt.getGeneratedKeys()) {
        rs.next();
        no = rs.getInt(1);
      }
      System.out.printf("입력된 게시글 번호: %d\n", no);

      try (PreparedStatement stmt2 = con.prepareStatement( //
          "insert into x_board_file(file_path,board_id) values(?,?)")) {
        stmt2.setString(1, filename);
        stmt2.setInt(2, no);
        stmt2.executeUpdate();
        System.out.println("첨부파일 등록 완료!");

      } catch (Exception e) {
        System.out.printf("첨부파일 등록 오류: %s\n", e.getMessage());
        // 첨부파일 데이터를 입력하는 중에 오류가 발생하더라도
        // 기존에 입력한 게시글은 계속 유효하다.
      }
    }
  }
}


