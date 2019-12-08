// dynamic SQL 다루기 - ${}를 안전하게 사용하는 방법 
package ch26.f;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test07_2 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/f/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    HashMap<String,Object> params = new HashMap<>();

    
    Scanner keyboard = new Scanner(System.in);
    System.out.print("정렬할 컬럼? ");
    String column = keyboard.nextLine();
    
    if (column.length() > 0) {
      // 사용자가 입력한 값을 그대로 컬럼명으로 지정하지 않고 
      // 다음과 같이 자바 코드로 컬럼명을 지정한다.
      switch (column) {
        case "title":
        case "contents":
        case "created_date":
        case "view_count": 
          params.put("column", column);
          break;
        default:
          params.put("column", "board_id");
      }

      System.out.print("정렬방식? ");
      String sort = "asc";
      if (keyboard.nextLine().equalsIgnoreCase("desc")) {
        sort = "desc";
      }
      params.put("sort", sort);
    }
    
    keyboard.close();
    
    // Test07 클래스와 달리 정렬할 컬럼의 이름과 정렬 방식에 대한 SQL 코드는 
    // 자바의 코드로 지정한 것이다.
    // 따라서 잘못된 SQL 코드가 삽입되는 것을 방지할 수 있다. 
    List<Board> boards = sqlSession.selectList("board.select7_ok", params);
    
    for (Board b : boards) {
      System.out.println(b);
    }
    System.out.println("-------------------------------");
    
  }

}






