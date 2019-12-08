// select 결과 값을 자바 인스턴스에 온전히 담는 방법 II
package ch26.d;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test04 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/d/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    // select 문에서 컬럼 값을 자바 인스턴스의 프로퍼티와 맞추기 위해 
    // 별명을 부여하는 방식은 번거롭다.
    // 특히 컬럼 개수가 많은 경우 더더욱 번거롭다.
    // 이를 해결하기 위해 mybatis는 컬럼의 이름과 프로퍼티 이름을 연결해주는 문법을 제공한다.
    //    <resultMap></resultMap>
    // 
    Board2 board = sqlSession.selectOne("board.select5"); 
    System.out.println(board);
  }

}






