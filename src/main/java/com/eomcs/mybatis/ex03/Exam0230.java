// dynamic sql 다루기 - <set> 사용법
package com.eomcs.mybatis.ex03;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0230 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex03/mybatis-config.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 실행 예:
    // => 게시글 변경하기
    // => 제목만 바꿀 경우, 내용만 바꿀 경우, 둘 다 바꿀 경우에 대해
    // <set>과 <if>를 이용하면 한 개의 SQL로 처리할 수 있다.

    HashMap<String, Object> params = new HashMap<>();

    Scanner keyScan = new Scanner(System.in);

    System.out.print("제목? ");
    String value = keyScan.nextLine();
    if (value.length() > 0) {
      params.put("title", value);
    }

    System.out.print("내용? ");
    value = keyScan.nextLine();
    if (value.length() > 0) {
      params.put("content", value);
    }

    keyScan.close();

    params.put("no", 1); // 1번 게시글 변경

    int count = sqlSession.update("BoardMapper.update4", params);
    System.out.println(count);

    // <if>
    // => 이 태그를 이용하여 사용자가 입력한 항목만 변경한다.
    //
    // <set>
    // => 이 태그를 이용하여 SQL 앞,뒤에 붙은 콤마(,)를 자동으로 제거한다.
    //
    // => dynamic sql 제조 방법은 SQL 작성을 줄이게 해준다.

    sqlSession.commit();
    sqlSession.close();
  }

}


