// insert 를 실행한 후 자동 생성된 PK 값 알아내기
package ch26.e;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test05 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/e/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    Board board = new Board();
    board.setTitle("오호라...제목이오!");
    board.setContents("내용이라네요...");
    
    sqlSession.insert("board.insert1", board);
    System.out.println(board);
    System.out.println("-------------------------------");
    
    // insert 한 후 자동 생성된 PK 값을 알고 싶으면 
    // SQL 매퍼 파일에 SQL문을 정의할 때 PK 값을 리턴해 달라고 설정해야 한다.
    sqlSession.insert("board.insert3", board);
    System.out.println(board);
    System.out.println("-------------------------------");
    
    
    List<Board> boards = sqlSession.selectList("board.select1");
    for (Board b : boards) {
      System.out.println(b);
    }
    System.out.println("-------------------------------");
  }

}






