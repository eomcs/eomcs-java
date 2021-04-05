// Mybatis 설정 파일 - 도메인 클래스의 별명을 설정하는 방법과 사용법
package com.eomcs.mybatis.ex01.d;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // mybatis 설정 파일에서 fully-qualified class name 을 사용하는 대신에
    // 짧은 이름으로 대체할 수 있다.
    //
    // <typeAliases>
    //   <typeAlias type="com.eomcs.mybatis.ex01.d.Board" alias="board"/>
    // </typeAliases>
    //
    // 그러면 이 별명을 어디에 사용하는가?
    // => SQL 맵퍼 파일에서 클래스를 지정할 때 사용한다.
    // <select id="selectBoard" resultType="board">
    //   select
    //       board_id as no,
    //       title,
    //       contents as content,
    //       created_date registeredDate,
    //       view_count viewCount
    //   from x_board
    // </select>
    //
    // 참고!
    // => 자바 primitive type과 wrapper 클래스, String 클래스,
    //     일부 유틸리티 클래스 등에 대해서는 mybatis가 미리 별명을 지정하였다.
    // => 예)
    //     int       ->  _int
    //     float     ->  _float
    //     java.lang.Integer     -> int
    //     java.lang.Float       -> float
    //     java.lang.String      -> string
    //     java.util.Map         -> map
    //     java.util.HashMap     -> hashMap
    //
    //
    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex01/d/mybatis-config.xml")).openSession();

    List<Board> boards = sqlSession.selectList("BoardMapper.selectBoard");

    System.out.println(boards.size());

    sqlSession.close();
  }

}


