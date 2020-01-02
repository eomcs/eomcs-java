// 게시판 관리 - JDBC 코드를 별도의 클래스로 캡슐화시킴. DAO 적용.
package com.eomcs.jdbc.ex3;

import java.util.Scanner;

public class Exam01_delete {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("삭제할 게시물 번호? ");
    String no = keyScan.nextLine();

    try {
      BoardDao boardDao = new BoardDao();
      int count = boardDao.delete(Integer.parseInt(no));
      System.out.printf("%d 개 삭제 성공!", count);
    } catch (Exception e) {
      e.printStackTrace();
    }

    keyScan.close();
  }
}







