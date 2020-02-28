// Mybatis - SQL에 파라미터 지정하기 : ${} 사용의 위험성
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0222 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex02/mybatis-config05.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // mybatis에 SQL문을 만들어 전달할 수 있다.
    // => SQL Mapper 에서는 ${} 문법으로 SQL 문을 받는다.
    // => 단, 사용자가 입력한 값을 그대로 전달한다면,
    // SQL 삽입 공격에 노출될 수 있기 때문에 위험하다.
    // => 사용자가 입력한 값을 그대로 전달하지 않고,
    // 개발자가 지정한 값을 전달한다면
    // 안전하게 사용할 수 있다.
    List<Board> list = sqlSession.selectList(//
        "BoardMapper.selectBoard3", "where title like '%oh%'");

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


