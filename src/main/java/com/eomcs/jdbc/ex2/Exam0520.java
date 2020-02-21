// 트랜잭션 다루기 - commit & rollback
package com.eomcs.jdbc.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Exam0520 {

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

      // 트랜잭션(transaction)?
      // => 여러 개의 데이터 변경 작업을 한 단위로 묶은 것.
      // => 한 단위로 묶인 모든 작업이 성공 했을 때 그 작업 결과를 저장한다.
      //
      // 여러 작업을 트랜잭션으로 묶는 방법?
      // => autocommit을 수동으로 전환한다.
      // => 모든 작업이 성공했을 때 저장한다. - commit
      // => 한 작업이라도 실패하면 기존에 작업한 결과를 취소한다. - rollback
      //
      // 1) 트랜잭션 시작 - 커넥션 객체의 오토커밋을 false로 지정한다.
      con.setAutoCommit(false);
      // 이후부터 이 커넥션으로 실행하는 모든 SQL은
      // commit을 요청하기 전에는 테이블에 그 결과를 적용하지 않는다.
      // 데이터를 변경하는 모든 작업은(insert|update|delete)
      // DBMS의 임시 테이블에 보관된다.
      // 연결이 끊어지면 임시 테이블에 보관된 데이터는 버려진다.
      // 따라서 연결을 끊기 전에 작업한 내용을 적용하고 싶다면,
      // 반드시 commit을 요청해야 한다.
      //

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

        // 트랜잭션 작업 승인
        // => 지금까지 수행한 작업을 저장하라고 서버에 요청한다.
        // => commit()을 호출하지 않으면 서버에 요청한 데이터 변경 작업은 자동 취소된다.
        //
        con.commit();

      } catch (Exception e) {
        System.out.printf("첨부파일 등록 오류: %s\n", e.getMessage());
        // 트랜잭션 작업 취소 - 서버의 요청한 작업을 취소할 것을 명령한다.
        // => rollback()은 명시적으로 작업 취소를 명령하는 것이다.
        // => 커넥션을 공유하는 상황에서는 이렇게 명시적으로 작업 취소를 명령하는 것이 좋다.
        // => 왜냐하면 같은 커넥션으로 다른 작업을 처리하는 경우 영향을 받을 수 있기 때문이다.
        //
        con.rollback();
      }
    }
  }
}


