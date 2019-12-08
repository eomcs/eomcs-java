// 트랜잭션 다루기 - Mybatis + SqlSessionFactoryProxy 로 트랜잭션 제어하기 
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test04 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/i/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    // 여러 메서드에서 같은 SqlSession을 사용하는 방법 I
    // => 멀티 스레드 환경이라 가정하자!
    // => 같은 스레드의 경우 같은 SqlSession을 사용하면 트랜잭션을 처리할 수 있다.
    // 해결책?
    // => SqlSessionFactory의 프록시를 만들어 SqlSession을 리턴할 때 
    //    같은 스레드에 대해 같은 SqlSession을 리턴하도록 한다.
    //
    SqlSessionFactoryProxy sqlSessionFactoryProxy = 
        new SqlSessionFactoryProxy(sqlSessionFactory);
    
    // SQL을 실행하는 메서드에는 원래의 SqlSessionFactory를 주지 않는다.
    // 대신 프록시 객체를 준다.
    try {
      insert1(sqlSessionFactoryProxy);
      insert2(sqlSessionFactoryProxy);
      sqlSessionFactoryProxy.openSession().commit();
    } catch (Exception e) {
      sqlSessionFactoryProxy.openSession().rollback();
    }
    printList(sqlSessionFactoryProxy);
    
    // SqlSession 객체의 사용이 끝났으면 close 한다.
    // 단 실제 SqlSessionProxy로 형변환 한 후 realClose()를 호출한다.
    ((SqlSessionProxy)sqlSessionFactoryProxy.openSession()).realClose();
  }
  
  static void insert1(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.insert("board.insert", new Board()
        .setTitle("a107")
        .setContents("내용7"));
    sqlSession.close();
  }
  
  static void insert2(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    Board board = new Board();
    board.setTitle("a108");
    board.setContents("내용8");
    sqlSession.insert("board.insert", board);
    sqlSession.close();
  }
  
  static void printList(SqlSessionFactory sqlSessionFactory) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    sqlSession.close();
  }
}






