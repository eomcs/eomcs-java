// Mybatis - SQL에 파라미터 지정하기 : ${}
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0220 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "step25/ex6/mybatis-config05.xml");
    SqlSessionFactory factory = 
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // mybatis에 SQL문을 만들어 전달할 수 있다.
    // => SQL 삽입 공격에 노출되기 때문에 이 방식을 사용하지 말라!
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("limitSQL", "limit 9, 5"); 

    List<Board> list = 
        sqlSession.selectList("BoardMapper.selectBoard", paramMap);

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s\n", 
          board.getNo(), 
          board.getTitle(), 
          board.getContent(),
          board.getRegisteredDate());
    }

    sqlSession.close();
  }

}











