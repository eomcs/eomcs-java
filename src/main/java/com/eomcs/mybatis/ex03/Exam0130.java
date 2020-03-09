// dynamic sql 다루기 - 조건문 사용 II
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0130 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 실행 예:
    // => 사용자로부터 검색 키워드를 입력 받아 조회한다.
    // => 제목, 내용, 번호로 검색하기

    Scanner keyScan = new Scanner(System.in);

    System.out.print("항목(1:번호, 2:제목, 3: 내용, 그 외: 전체)? ");
    String item = keyScan.nextLine();

    System.out.print("검색어? ");
    String keyword = keyScan.nextLine();

    keyScan.close();

    // SQL 매퍼에 여러 개의 파라미터 값을 넘길 때 주로 Map을 사용한다.
    HashMap<String, Object> params = new HashMap<>();
    params.put("item", item);
    params.put("keyword", keyword);

    List<Board> list = sqlSession.selectList("BoardMapper.select4", //
        params);

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


