// #{} 과 ${} 차이점 => ${} 문법의 쓰임새
package com.eomcs.mybatis.ex03.f;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.mybatis.vo.Board;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex03/f/mybatis-config.xml")).openSession();

    // 정렬 방식을 파라미터로 넘기기
    // => ${} 문법은 파라미터 값을 SQL 문에 그대로 삽입한다.
    //
    List<Board> boards = sqlSession.selectList("BoardMapper.selectBoard2", "desc");

    for (Board b : boards) {
      System.out.printf("%d,%s,%s,%s,%d\n",
          b.getNo(),
          b.getTitle(),
          b.getContent(),
          b.getRegisteredDate(),
          b.getViewCount());
    }

    sqlSession.close();
  }

}


