// dynamic sql 다루기 - 조건문 사용 전
package com.eomcs.mybatis.ex03x;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 실행 예:
    // => 사용자로부터 게시글의 번호를 입력 받아 조회한다.
    // => 만약 오류가 발생하면 전체 게시글을 출력한다.

    Scanner keyScan = new Scanner(System.in);
    System.out.print("게시글 번호? ");
    String str = keyScan.nextLine();
    keyScan.close();

    List<Board> list = null;

    try {
      // dynamic SQL 문법을 사용하기 전:
      // => 게시글 번호가 주어지면 특정 게시글만 조회하는
      // select1 SQL을 실행한다.
      list = sqlSession.selectList("BoardMapper.select1", Integer.parseInt(str));

    } catch (Exception e) {
      // => 게시글 번호가 없으면 전체 게시글을 조회하는
      // select2 SQL을 실행한다.
      list = sqlSession.selectList("BoardMapper.select2");
    }

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %d\n", //
          board.getNo(), //
          board.getTitle(), //
          board.getRegisteredDate(), //
          board.getViewCount());
    }

    sqlSession.close();
  }

}


