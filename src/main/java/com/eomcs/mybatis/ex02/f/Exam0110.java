// SqlSession 사용법 - 결과가 여러 개일 때 => selectOne() 사용하면?
package com.eomcs.mybatis.ex02.f;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.mybatis.vo.Board;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/f/mybatis-config.xml")).openSession();

    // selectOne()
    // - select 결과가 0 또는 1개 일 때 호출할 수 있다.
    // - 여러 개의 결과가 나오는 select 문에 대해 호출하면 예외가 발생한다.
    // - 리턴 값은 한 개의 결과 레코드 값을 담은 객체이다.
    // 
    Board b = sqlSession.selectOne("BoardMapper.selectBoard1");

    System.out.printf("%d,%s,%s,%s,%d\n",
        b.getNo(),
        b.getTitle(),
        b.getContent(),
        b.getRegisteredDate(),
        b.getViewCount());

    sqlSession.close();
  }

}


