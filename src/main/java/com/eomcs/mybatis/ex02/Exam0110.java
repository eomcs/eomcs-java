// Mybatis - select 컬럼과 프로퍼티
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(
        "step25/ex6/mybatis-config01.xml");
    SqlSessionFactory factory = 
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    List<Board> list = 
        sqlSession.selectList("BoardMapper.selectBoard");

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s\n", 
          board.getNo(), 
          board.getTitle(), 
          board.getContent(),
          board.getRegisteredDate());
    }
    // 실행 오류 발생! 이유?
    // => mybatis에서 결과 값을 Board 객체에 담지 못했기 때문이다.
    // 
    // 왜 결과 값을 Board에 담지 못했는가?
    // => mybatis에서 결과의 컬럼 값을 자바 객체에 담을 때 
    //    컬럼 이름과 같은 이름을 가진 프로퍼티(셋터 메서드)를 찾는다.
    // => 그런데 Board 클래스에는 컬럼 이름과 일치하는 프로퍼티가 없다.

    sqlSession.close();
  }

}











