// 게시판 관리 - 변경 + PreparedStatement 적용
package com.eomcs.jdbc.ex2;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exam02_update {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("변경할 게시물 번호? ");
    String no = keyScan.nextLine();

    System.out.print("제목? ");
    String title = keyScan.nextLine();

    System.out.print("내용? ");
    String content = keyScan.nextLine();

    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false",
        "java106", "1111");

    PreparedStatement stmt = con.prepareStatement(
        "update ex_board set titl=?, cont=?, rdt=now() where bno=?");
    stmt.setString(1, title);
    stmt.setString(2, content);
    stmt.setString(3, no);
    int count = stmt.executeUpdate();

    System.out.printf("%d 개 변경 성공!", count);

    stmt.close();
    con.close();
    keyScan.close();
  }
}







