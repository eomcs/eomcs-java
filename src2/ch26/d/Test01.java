// selectOne()과 selectList()
package ch26.d;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/d/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    // selectList()
    // => 여러 개의 결과 값을 리턴하는 select를 실행할 때 사용한다.
    // => select 실행으로 생성된 각 row의 값은 resultType에 지정한 클래스의 인스턴스에 저장된다.
    // => 그리고 그 인스턴스는 List 구현체에 담겨 리턴된다.
    // 
    List<Board> list = sqlSession.selectList("board.select1");
    for (Board b : list) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          b.getBoard_id(),
          b.getTitle(),
          b.getContents(),
          b.getCreated_date(),
          b.getView_count());
    }
    System.out.println("----------------------------------");
    
    // selectOne()
    // => 한 개의 결과 값을 리턴하는 select를 실행할 때 사용한다.
    // => 만약 여러 개의 결과가 리턴되는 select를 실행한다면 예외가 발생한다.
    
    // board.select1 SQL 문은 여러 개의 결과 값이 리턴되기 때문에 
    // selectOne()으로 실행해서는 안된다. 
    // 실행중 오류가 발생한다.
    // selectList()를 실행해야 한다.
    //Board board = sqlSession.selectOne("board.select1");
    
    Board board = sqlSession.selectOne("board.select2"); // OK!
    
    System.out.println(board);
    
    // 두 개 이상의 결과를 리턴하는 select 문을 실행할 때는 selectOne()를 사용해서는 안된다.
    // 오류 발생!
    //sqlSession.selectOne("board.select1"); // Error!
  }

}






