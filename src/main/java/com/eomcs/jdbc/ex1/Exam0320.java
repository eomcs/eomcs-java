// JDBC 프로그래밍 - DBMS에 SQL문 보내기 : select
package com.eomcs.jdbc.ex1;

import java.sql.DriverManager;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    try (
        java.sql.Connection con = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/studydb?user=study&password=1111");
        java.sql.Statement stmt = con.createStatement();

        // executeQuery()
        // => DBMS 서버에 select 문을 보낸다.
        // => 리턴 값: java.sql.ResultSet 구현 객체
        //
        // ResultSet?
        // => 결과가 아니다! 서버에서 결과를 가져오는 일을 할 객체이다.
        // => 즉 서버의 select 실행 결과를 가져올 때 사용하는 도구이다.
        //
        java.sql.ResultSet rs = stmt.executeQuery("select * from x_board order by board_id desc");

    ) {

      // ResultSet 객체를 사용하여 서버에서 select의 결과 한 레코드(row)를 가져온다.
      //
      boolean isReceived = rs.next(); // 가져왔으면 true, 가져올 게 없다면 false
      // 용어정리
      // "레코드(record)"
      // => select를 실행한 후 생성된 결과이다.
      //

      if (isReceived) {
        // 서버에서 한 개 가져온 결과를 출력한다.
        System.out.printf("%d, %s, %s, %s, %d\n", //
            rs.getInt(1), // board_id
            rs.getString(2), // title
            rs.getString(3), // contents
            rs.getDate(4), // created_date
            rs.getInt(5)); // view_count

        // getXxx():
        // => DBMS에 설정된 컬럼의 타입에 따라 getXxx()를 호출한다.
        // => int, number: getInt()
        // => char, varchar, text: getString()
        // => date, datetime: getDate()
        // => float: getFloat()
        //
        // getXxx(컬럼번호)
        // => select 문에 나열한 컬럼의 순서를 지정한다.
        // 단 번호는 0부터가 아니라 1부터 지정한다.
        // => select 문에 wildcard(*)를 사용했다면,
        // 테이블을 정의할 때 선언한 컬럼의 순서이다.
        //
        // 컬럼의 타입과 상관없이 getString()으로 값을 꺼낼 수 있다.

      } else {
        System.out.println("서버에서 한 개의 레코드를 가져오지 못했다!");
      }
    }
  }
}


