// java.sql.Driver 구현체 자동 등록
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test02 {

  public static void main(String[] args) {
    try {
      // java.sql.Driver 인터페이스를 구현한 클래스를 로딩하면 
      // 해당 클래스에서 자신을 자동으로 DriverManager에게 등록할 것이다.
      // org.mariadb.jdbc.Driver 클래스의 소스를 확인해 보라!
      // static 블록에서 Driver 객체를 만들어 DriverManager에게 등록한다.
      //
      // 이 방식의 장점은 소스 코드에 특정 클래스를 지정하지 않는다는 것이다.
      // 클래스 이름을 문자열로 전달할 수 있기 때문에 
      // 특정 클래스에 종속되지 않게 작성할 수 있다.
      Class.forName("org.mariadb.jdbc.Driver");
      
    } catch (Exception e) {
      System.out.println("MariaDB의 java.sql.Driver 구현체를 등록하는 중에 오류 발생!");
      e.printStackTrace();
    }
    
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb", "bitcamp", "1111")) {
      System.out.println("DBMS에 연결 성공!");
      
    } catch (Exception e) {
      System.out.println("DBMS에 연결 중 오류 발생!");
      e.printStackTrace();
    }
    
  }

}









