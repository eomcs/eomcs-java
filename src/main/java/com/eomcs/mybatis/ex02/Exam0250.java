// Mybatis - Update SQL 실행하기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0250 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex02/mybatis-config08.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 변경할 데이터를 객체에 담아서 넘긴다.
    Board board = new Board();
    board.setNo(5);
    board.setTitle("aaaa");
    board.setContent("bbbb");

    int count = sqlSession.update("BoardMapper.updateBoard", board);
    System.out.println(count);
    sqlSession.commit();
    // commit 명령을 내리지 않으면 insert/update/delete을 테이블에 반영하지 않는다.
    // close() 할 때 취소된다.

    sqlSession.close();
  }

}


