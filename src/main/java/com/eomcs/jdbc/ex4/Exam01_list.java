// 게시판 관리 - DB 커넥션 풀 적용
package com.eomcs.jdbc.ex4;

import java.util.List;

public class Exam01_list {

  public static void main(String[] args) throws Exception {
    DataSource dataSource = new DefaultDataSource();
    BoardDao boardDao = new BoardDao(dataSource);

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







