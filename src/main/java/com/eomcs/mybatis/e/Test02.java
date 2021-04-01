// SQL을 실행할 때 파라미터 값 넘기기 II - 도메인 객체 넘기기
package ch26.e;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test02 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/e/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    // insert 문을 실행할 때는 insert() 메서드를 호출하라.
    // => 그러나 update(), delete() 메서드를 호출해도 된다.
    //    왜? insert, update, delete 모두 JDBC에서 executeUpdate()를 호출하기 때문이다.
    // => 하지만 SQL의 명령과 메서드 이름을 가능한 같게 하라!
    Board board = new Board();
    board.setTitle("오호라...제목이오!");
    board.setContents("내용이라네요...");
    
    // insert(sql id, 파라미터값을 담은 객체)
    // => 리턴 값은 insert 된 row의 개수이다.
    int count = sqlSession.insert("board.insert1", board);
    System.out.println(count);
    
    // insert 할 때 파라미터를 맵 객체에 담아 넘겨도 된다.
    // => Map 객체에 값을 저장할 때 사용하는 key 값은 SQL 맵퍼에서 프로퍼티명으로 사용된다.
    HashMap<String,Object> board2 = new HashMap<>();
    board2.put("title", "오호...제목!");
    board2.put("contents", "오호..내용!");
    count = sqlSession.insert("board.insert2", board2);
    System.out.println(count);
    
    // 주의!
    // => mybatis는 auto commit이 false 이다.
    // => 데이터 변경(insert/update/delete) 작업을 수행한 후 반드시 commit()을 호출해야 한다.
    sqlSession.commit();
    
    List<Board> boards = sqlSession.selectList("board.select1");
    for (Board b : boards) {
      System.out.println(b);
    }
    System.out.println("-------------------------------");
  }

}






