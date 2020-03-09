// Mybatis - Delete SQL 실행하기
package com.eomcs.mybatis.ex02;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0260 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex02/mybatis-config09.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    // 먼저 자식 테이블의 데이터를 지운다.
    int count = sqlSession.delete("BoardMapper.deleteBoardFile", 3);
    System.out.println(count);

    // 그런 후 부모 테이블의 데이터를 지운다.
    count = sqlSession.delete("BoardMapper.deleteBoard", 3);
    System.out.println(count);

    sqlSession.commit();
    // commit 명령을 내리지 않으면 insert/update/delete을 테이블에 반영하지 않는다.
    // close() 할 때 취소된다.

    sqlSession.close();
  }

}


