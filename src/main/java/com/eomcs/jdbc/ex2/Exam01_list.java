// 게시물 관리 - 목록
package com.eomcs.jdbc.ex2;

import java.sql.DriverManager;

public class Exam01_list {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/java106db?serverTimezone=UTC&useSSL=false",
        "java106", "1111");
    java.sql.Statement stmt = con.createStatement();
    java.sql.ResultSet rs = stmt.executeQuery(
        "select bno,titl,rdt from ex_board");
    while (rs.next()) {
      System.out.printf("%d,%s,%s\n", 
          rs.getInt("bno"),  
          rs.getString("titl"),
          rs.getDate("rdt")); 
    }

    rs.close();
    stmt.close();
    con.close();
  }
}







