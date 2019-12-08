// dynamic SQL 다루기 - 조건문
package ch26.f;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/f/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    // dynamic sql?
    // => 조건에 따라 SQL이 변경되는 것.
    // => mybatis는 이를 위해 조건문, 반복문 등을 다룰 수 있도록 특별한 태그를 제공한다.
    
    // 조건문 사용하기
    // => 검색어의 여부에 따라 where 절을 추가를 조정해보자!
    Scanner keyboard = new Scanner(System.in);
    System.out.print("게시물 번호? ");
    String keyword = keyboard.nextLine();
    keyboard.close();
    
    List<Board> boards = null;
    
    try {
      boards = sqlSession.selectList("board.select1", Integer.parseInt(keyword));
    } catch (Exception e) {
      boards = sqlSession.selectList("board.select0");
    }
    
    for (Board b : boards) {
      System.out.println(b);
    }
    System.out.println("-------------------------------");
    
  }

}






