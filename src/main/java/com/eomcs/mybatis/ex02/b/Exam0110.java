// SqlSession 사용법 - select 문 실행하기 : 목록 출력 => selectList()
package com.eomcs.mybatis.ex02.b;

import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.mybatis.vo.Board;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/mybatis/ex02/b/mybatis-config.xml")).openSession();

    // selectList()
    // => select 문의 결과가 여러 개일 때 사용한다.
    // => 결과가 없으면 size 가 0인 List 객체를 리턴한다.
    //
    List<Board> boards = sqlSession.selectList("BoardMapper.selectBoard");

    for (Board b : boards) {
      System.out.printf("%d,%s,%s,%s,%d\n",
          b.getNo(),
          b.getTitle(),
          b.getContent(),
          b.getRegisteredDate(),
          b.getViewCount());
    }
    // 실행 결과가 기대와 같지 않은 이유?
    // => mybatis에서 결과 값을 Board 객체에 담을 때 일부 컬럼의 값을 담지 못했기 때문이다.
    //
    // 왜 일부 컬럼의 값을 Board에 담지 못했는가?
    // => mybatis에서 결과의 컬럼 값을 자바 객체에 담을 때
    //    컬럼 이름과 같은 이름을 가진 프로퍼티(셋터 메서드)를 찾는다.
    // => 컬럼 이름과 일치하는 프로퍼티가 없다면,
    //    셋터 메서드를 호출할 수 없기 때문에 
    //    해당 컬럼의 값이 자바 객체에 저장되지 못한다.

    sqlSession.close();
  }

}


