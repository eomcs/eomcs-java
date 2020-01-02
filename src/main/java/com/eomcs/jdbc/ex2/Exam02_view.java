// 게시물 관리 - 조회 + PreparedStatement 적용
package com.eomcs.jdbc.ex2;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Exam02_view {

  public static void main(String[] args) throws Exception {
    Scanner keyScan = new Scanner(System.in);
    System.out.print("조회할 게시물 번호? ");
    String no = keyScan.nextLine();

    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false",
        "java106", "1111");

    PreparedStatement stmt = con.prepareStatement(
        "select bno,titl,cont,rdt from ex_board where bno=?");
    stmt.setString(1, no);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
      System.out.printf("번호: %d\n", rs.getInt("bno"));
      System.out.printf("제목: %s\n", rs.getString("titl"));
      System.out.printf("내용: %s\n", rs.getString("cont"));
      System.out.printf("등록일: %s\n", rs.getDate("rdt")); 
    } else {
      System.out.println("해당 번호의 게시물이 없습니다!");
    }

    rs.close();
    stmt.close();
    con.close();
    keyScan.close();
  }
}







