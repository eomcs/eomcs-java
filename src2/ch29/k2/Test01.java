// Mybatis와 스프링 연동하기 - XML 사용하여 연동하기
package ch29.k2;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;
import ch29.k2.dao.BoardDao;
import ch29.k2.vo.Board;

public class Test01 {
  public static void main(String[] args) {
    
    // XML 설정 파일을 사용할 때는 ClassPathXmlApplicationContext 클래스를 이용한다.
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext(
              "ch29/k2/application-context.xml",
              "ch29/k2/database-context.xml",
              "ch29/k2/mybatis-context.xml");
    
    System.out.println("---------------------------------------");
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    BoardDao boardDao = (BoardDao) iocContainer.getBean("boardDao");
    List<Board> list = boardDao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    
    /*
    System.out.println("---------------------------------------");
    
    Board board = new Board();
    board.setTitle("제목입니다.");
    board.setContents("내용입니다.");
    boardDao.insert(board);
    System.out.println("입력 완료!");
    
    System.out.println("---------------------------------------");
    
    list = boardDao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    
    System.out.println("---------------------------------------");
    
    board = boardDao.findByNo(7);
    System.out.println(board);
    
    System.out.println("---------------------------------------");
    
    board.setTitle("오호라..변경");
    boardDao.update(board);
    System.out.println("변경 완료!");
    
    System.out.println("---------------------------------------");
    
    board = boardDao.findByNo(7);
    System.out.println(board);
    
    System.out.println("---------------------------------------");
    
    boardDao.delete(7);
    System.out.println("삭제 완료!");
    
    System.out.println("---------------------------------------");
    
    list = boardDao.findAll();
    for (Board b : list) {
      System.out.println(b);
    }
    */
  }
}






