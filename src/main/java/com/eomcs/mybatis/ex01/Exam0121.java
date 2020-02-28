// Mybatis - 컬럼 이름과 프로퍼티 이름을 일치시키기
package com.eomcs.mybatis.ex01;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0121 {

  public static void main(String[] args) throws Exception {
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(//
        Resources.getResourceAsStream(//
            "com/eomcs/mybatis/ex01/mybatis-config.xml"));

    SqlSession sqlSession = factory.openSession();

    List<Board> list = sqlSession.selectList("BoardMapper.selectBoard2");

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s, %d\n", board.getNo(), board.getTitle(), board.getContent(),
          board.getRegisteredDate(), board.getViewCount());
    }
    sqlSession.close();
  }
}

// 컬럼 이름과 자바 객체의 프로퍼티 이름을 같게 하기
// ---------------------------------------------------------------------
// <select id="selectBoard" resultType="com.eomcs.mybatis.ex01.Board">
// select
//   board_id as no,
//   title,
//   contents as content,
//   created_date as registeredDate,
//   view_count as viewCount
// from x_board
// </select>
// ---------------------------------------------------------------------
// => 컬럼의 값을 자바 객체에 담으려면 컬럼과 같은 이름의 프로퍼티가 있어야 한다.
// => 없다면 위와 같이 프로퍼티 명을 컬럼의 별명으로 지정하라.
//


