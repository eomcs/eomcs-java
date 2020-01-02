// 게시판 관리 - JDBC 코드를 별도의 클래스로 캡슐화시킴. DAO 적용.
package com.eomcs.jdbc.ex3;

import java.util.Scanner;

public class Exam01_view {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("조회할 게시물 번호? ");
    String no = keyScan.nextLine();

    try {
      BoardDao boardDao = new BoardDao();
      Board board = boardDao.view(no);
      if (board == null) {
        System.out.println("해당 번호의 게시물이 없습니다!");
      } else {
        System.out.printf("번호: %d\n", board.getNo());
        System.out.printf("제목: %s\n", board.getTitle());
        System.out.printf("내용: %s\n", board.getContent());
        System.out.printf("등록일: %s\n", board.getRegisteredDate());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}







