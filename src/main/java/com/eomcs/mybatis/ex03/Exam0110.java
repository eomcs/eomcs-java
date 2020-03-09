// dynamic sql 다루기 - 조건문 사용 전
package com.eomcs.mybatis.ex03;

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

    // dynamic sql?
    // => 조건에 따라 SQL을 달리 생성하는 것
    // => mybatis는 이를 위해 조건에 따라 SQL을 변경하거나,
    // 동일한 SQL을 반복적으로 생성할 수 있는 문법을 제공한다.
    //
    // 실행 예:
    // => 사용자로부터 게시글의 번호를 입력 받아 조회한다.
    // => 만약 오류가 발생하면 전체 게시글을 출력한다.

    Scanner keyScan = new Scanner(System.in);
    System.out.print("게시글 번호? ");
    String str = keyScan.nextLine();
    keyScan.close();

    List<Board> list = null;

    try {
      // 게시글 번호가 주어지면 해당 게시글만 출력한다.
      list = sqlSession.selectList("BoardMapper.select1", Integer.parseInt(str));
    } catch (Exception e) {
      // 게시글 번호가 없으면 전체 게시글을 출력한다.
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


