// Mybatis - SQL에 파라미터 지정하기 : #{} 의 한계
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0220 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex02/mybatis-config05.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 예) 파라미터로 컬럼 이름을 넘겨주면
    // 해당 컬럼의 값을 오름차순으로 정렬한다.
    List<Board> list = sqlSession.selectList(//
        "BoardMapper.selectBoard1", "title");
    // => 파라미터 값을 SQL에 그대로 삽입하려면
    // #{} 문법을 사용해서는 안된다.
    // ${} 문법을 사용해야 한다.
    // => #{}은 값을 넣을 때 사용한다.
    // => 현재 selectBoard1에서는 #{}을 사용했기 때문에
    // order by가 정상적으로 적용되지 못했다.

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %s\n", //
          board.getNo(), //
          board.getTitle(), //
          board.getContent(), //
          board.getRegisteredDate());
    }

    sqlSession.close();

    // mybatis는 private 셋터도 호출한다!
    // 어떻게?
    //
    /*
    Board b = new Board();
    Method m = Board.class.getDeclaredMethod("setTitle", String.class);
    m.setAccessible(true);
    m.invoke(b, "오호라!");
     */
  }

}


