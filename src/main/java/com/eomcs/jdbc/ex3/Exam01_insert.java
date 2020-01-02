// 게시판 관리 - JDBC 코드를 별도의 클래스로 캡슐화시킴. DAO 적용.
package com.eomcs.jdbc.ex3;

import java.util.Scanner;

public class Exam01_insert {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);

    // 사용자로부터 제목, 내용을 입력 받는다.
    Board board = new Board();

    System.out.print("제목? ");
    board.setTitle(keyScan.nextLine());

    System.out.print("내용? ");
    board.setContent(keyScan.nextLine());

    try {
      BoardDao boardDao = new BoardDao();
      int count = boardDao.insert(board);
      System.out.printf("%d 개 입력 성공!", count);
    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}







