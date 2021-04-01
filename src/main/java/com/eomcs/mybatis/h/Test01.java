// 조인 데이터 다루기 - join 사용 전
package ch26.h;

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
        "ch26/h/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    Scanner keyboard = new Scanner(System.in);
    System.out.print("게시물 번호? ");
    int no = Integer.parseInt(keyboard.nextLine());
    keyboard.close();
    
    // 조인을 사용하지 않고 자식 테이블의 데이터를 조회하려면 
    // 다음과 같이 여러 번 SQL을 실행해야 한다.
    //
    Board board = sqlSession.selectOne("board.selectBoard", no);
    System.out.println(board);
    
    List<AttachFile> files = sqlSession.selectList("board.selectAttachFile", no);
    System.out.println("[첨부파일]");
    for (AttachFile f : files) {
      System.out.println("  => " + f);
    }
    System.out.println("-------------------------------");
    
  }

}






