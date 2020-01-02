// 게시판 관리 - 변경
package com.eomcs.jdbc.ex2;

import java.sql.DriverManager;
import java.util.Scanner;

public class Exam01_update {

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
    java.sql.Statement stmt = con.createStatement();

    String sql = String.format(
        "update ex_board set titl='%s', cont='%s', rdt=now() where bno=%s", 
        title, content, no);
    int count = stmt.executeUpdate(sql);
    System.out.printf("%d 개 변경 성공!", count);

    stmt.close();
    con.close();
    keyScan.close();
  }
}







