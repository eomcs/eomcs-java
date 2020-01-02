// 게시판 관리 - DB 커넥션 풀 적용
package com.eomcs.jdbc.ex4;

import java.util.Scanner;

public class Exam01_update {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);

    Board board = new Board();

    System.out.print("변경할 게시물 번호? ");
    board.setNo(Integer.parseInt(keyScan.nextLine()));

    System.out.print("제목? ");
    board.setTitle(keyScan.nextLine());

    System.out.print("내용? ");
    board.setContent(keyScan.nextLine());

    try {
      DataSource dataSource = new DefaultDataSource();
      BoardDao boardDao = new BoardDao(dataSource);
      int count = boardDao.update(board);
      System.out.printf("%d 개 변경 성공!", count);
    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}







