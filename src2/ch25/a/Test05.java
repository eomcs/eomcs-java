// java.sql.Connection 객체 
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test05 {

  public static void main(String[] args) {
    // DriverManager.getConnetion()
    // => org.mariadb.jdbc.Driver.connect()
    //  
    try (Connection con = DriverManager.getConnection(
        "jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=11112")) {
      System.out.println("DBMS에 연결됨!");
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}









