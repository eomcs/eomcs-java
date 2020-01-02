// 게시물 관리 - JDBC 코드를 별도의 클래스로 캡슐화시킴. DAO 적용.
package com.eomcs.jdbc.ex3;

import java.util.List;

public class Exam01_list {

  public static void main(String[] args) throws Exception {
    BoardDao boardDao = new BoardDao();

    try {
      List<Board> list = boardDao.list();

      for (Board board : list) {
        System.out.printf("%d,%s,%s\n", 
            board.getNo(),  
            board.getTitle(),
            board.getRegisteredDate()); 
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}







