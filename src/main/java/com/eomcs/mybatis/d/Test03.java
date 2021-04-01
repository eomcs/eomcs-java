// select 결과 값을 자바 인스턴스에 온전히 담는 방법
package ch26.d;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test03 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/d/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    // select 문에서 컬럼을 지정할 때 자바 인스턴스의 프로퍼티 이름과 다르다면 
    // 별명을 이용하여 프로퍼티명과 같게 한다.
    Board2 board = sqlSession.selectOne("board.select4"); 
    System.out.println(board);
  }

}






