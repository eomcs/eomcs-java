// 게시판 관리 - 삭제 + PreparedStatement 적용
package com.eomcs.jdbc.ex2;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exam02_delete {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("삭제할 게시물 번호? ");
    String no = keyScan.nextLine();

    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false",
        "java106", "1111");

    PreparedStatement stmt = con.prepareStatement(
        "delete from ex_board where bno=?");
    stmt.setString(1, no);
    int count = stmt.executeUpdate();

    System.out.printf("%d 개 삭제 성공!", count);

    // 자원해제
    // => 파일과 마찬가지로 DBMS에 연결한 후 더이상 사용하지 않으면 연결을 해제해야 한다.
    stmt.close();
    con.close();
    keyScan.close();
  }
}







