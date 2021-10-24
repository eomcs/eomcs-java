// 예외 처리 후
package com.eomcs.exception.ex5;

import java.sql.Date;
import java.util.Scanner;

public class Exam0120 {
  // RuntimeException 계열의 예외는 굳이 throws 문장을 선언하지 않아도 되지만,
  // read()를 호출하는 개발자에게 어떤 예외가 발생할 수 있는지
  // 명확하게 제시해주는 것이 유지보수에 도움이 되기 때문에
  // 메서드 선언부에 발생되는 예외를 명시하는 것이 좋다.
  //
  // => 그런데 조금 아쉬움이 있는 점은
  //    read() 메서드를 사용하는 개발자가
  //    이 메서드에서 RuntimeException을 던진다는 의미에 대해
  //    직관적으로 이해하기는 어렵다.
  //    그냥 RuntimeException을 던진다고 하니, 
  //    예외를 던진다는 것은 이해하지만,
  //    그 예외가 의미하는 바가 무엇인지 즉시 알아보기 힘들다는 얘기다.
  //
  static Board read() throws RuntimeException {
    try (Scanner keyScan = new Scanner(System.in)) {
      Board board = new Board();

      System.out.print("번호> ");
      board.setNo(Integer.parseInt(keyScan.nextLine()));

      System.out.print("제목> ");
      board.setTitle(keyScan.nextLine());

      System.out.print("내용> ");
      board.setContent(keyScan.nextLine());

      System.out.print("등록일> ");
      board.setCreatedDate(Date.valueOf(keyScan.nextLine()));

      return board;
    }
  }

  public static void main(String[] args) {
    try {
      Board board = read();
      System.out.println("---------------------");
      System.out.printf("번호: %d\n", board.getNo());
      System.out.printf("제목: %s\n", board.getTitle());
      System.out.printf("내용: %s\n", board.getContent());
      System.out.printf("등록일: %s\n", board.getCreatedDate());

    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      System.out.println("게시물 입력 중에 오류 발생!");
      // e.printStackTrace();
    }

  }
}


