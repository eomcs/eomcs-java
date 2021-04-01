// 조인 데이터 다루기 - join 사용 후
package ch26.h;

import java.io.InputStream;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test02 {

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
    
    // 조인을 사용할 경우 부모 객체 안에 자식 테이블의 정보를 받을 수 있다.
    // 작업:
    // 1) 부모 테이블의 데이터를 받는 클래스(ex: Board)에 
    //    자식 테이블의 데이터를 받는 필드(attachFiles)를 선언하라!
    // 2) SQL 매퍼 파일에서 <resultMap> 태그에 조인 정보를 정의한다.
    //
    // => 조인을 이용할 경우 다음과 같이 한 번만 SQL을 실행하면 된다.
    //    mybatis가 SQL 매퍼에 정의된 대로 자식 테이블의 데이터까지 인스턴스로 만들어 리턴해준다.
    Board board = sqlSession.selectOne("board.selectBoardWithAttachFile", no);
    System.out.println(board);
    
    System.out.println("[첨부파일]");
    for (AttachFile f : board.getAttachFiles()) {
      System.out.println("  => " + f);
    }
    System.out.println("-------------------------------");
    
  }

}
/*
select 
  b.board_id,
  b.title,
  b.contents,
  b.created_date,
  b.view_count,
  f.board_file_id,
  f.file_path
from x_board b
  left outer join x_board_file f on b.board_id=f.board_id
 */
















