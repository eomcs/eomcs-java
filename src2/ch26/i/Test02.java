// 트랜잭션 다루기 - commit() 호출 후
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test02 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/i/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    Board board = new Board();
    board.setTitle("a101");
    board.setContents("내용");
    
    sqlSession.insert("board.insert", board);
    
    board = new Board();
    board.setTitle("a102");
    board.setContents("내용");
    sqlSession.insert("board.insert", board);
    
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    
    // 이 sqlSession 객체로 실행한 모든 insert/update/delete 결과는 
    // 실제 테이블에 적용한다.
    sqlSession.commit();
    
    sqlSession.close();
  }
}






