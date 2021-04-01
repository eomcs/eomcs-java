// Mybatis 설정하기 II
// => DB 접속 정보를 mybatis 설정 파일로부터 분리하여 저장한다.
// => DB 접속 정보를 분리하면 DB 접속 정보가 변경되었을 때 
//    설정파일에 영향을 주지않고 바꿀 수 있어서 실무에서는 이 방식을 사용한다.
package ch26.b;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {
    
    // 1) jdbc.properties 파일을 추가한다.
    //    => 이 파일에 DB 접속 정보를 저장한다.
    // 2) mybatis-config.xml 파일을 변경한다.
    //    => DB 접속 정보를 설정할 때 jdbc.properties 파일의 데이터를 사용한다.
    // 3) BoardMapper.xml 파일 변경
    //    => 클래스의 패키지 명을 변경한다.
    //
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/b/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    List<Board> list = sqlSession.selectList("board.select1");
    for (Board b : list) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          b.getBoard_id(),
          b.getTitle(),
          b.getContents(),
          b.getCreated_date(),
          b.getView_count());
    }
  }

}






