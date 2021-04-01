// Mybatis - SQL에 파라미터 지정하기: String 값 넘겨주기
package com.eomcs.mybatis.ex02x;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0211 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex02/mybatis-config04.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // SQL을 실행할 때 파라미터 값을 전달하려면
    // 두 번째 파라미터로 전달해야 한다.
    // 여러 개의 값을 전달해야 한다면,
    // Map 객체에 담아 전달하라!

    // 예) 게시글 제목에 ohora 를 포함한 게시글을 찾는다.
    List<Board> list = sqlSession.selectList(//
        "BoardMapper.selectBoard2", "%ohora%");

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s\n", //
          board.getNo(), //
          board.getTitle(), //
          board.getContent(), //
          board.getRegisteredDate());
    }

    sqlSession.close();
  }

}


