// 조인 데이터 가져오기 - 한 개의 객체에 담기
package com.eomcs.mybatis.ex04;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    InputStream inputStream = Resources.getResourceAsStream(//
        "com/eomcs/mybatis/ex04/mybatis-config.xml");
    SqlSessionFactory factory = //
        new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = factory.openSession();

    Board board = sqlSession.selectOne("BoardMapper2.selectBoardWithFile", 1);

    System.out.println("[게시글 조회]");
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
    System.out.println();

    // 조인을 사용하면,
    // 게시글 객체 안에 첨부파일 객체가 들어 있다.
    // 그래서 따로 첨부파일을 select 할 필요가 없다.

    System.out.println("[첨부파일]");
    for (AttachFile file : board.getFiles()) {
      System.out.printf("%d, %s\n", file.getNo(), file.getFilePath());
    }

    // 구동원리:
    // ResultSet rs = stmt.executeQuery(
    // "select"
    // " b.board_id,"
    // + " b.title, "
    // + " b.contents, "
    // + " b.created_date,"
    // + " b.view_count,"
    // + " f.board_file_id,"
    // + " f.file_path "
    // + "from x_board b "
    // + " left outer join x_board_file f on b.board_id=f.board_id"
    // + "where b.board_id = 1");
    //
    // ArrayList<Board> boardList = new ArrayList<>();
    // int boardNo = 0;
    // Board board = null;
    // ArrayList<AttachFile> files = null;
    // while (rs.next()) {
    // if (boardNo != rs.getInt("board_id")) {
    // board = new Board();
    // board.setNo(rs.getInt("board_id"));
    // board.setTitle(rs.getString("title"));
    // board.setContent(rs.getString("contents"));
    // ...
    // files = new ArrayList<>();
    // board.setFiles(files);
    // boardNo = board.getNo();
    // }
    // AttachFile file = new AttachFile();
    // file.setNo(rs.getInt("board_file_id"));
    // file.setFilePath(rs.getString("file_path"));
    // file.setBoardNo(rs.getInt("board_id"));
    // files.add(file);
    // }



    sqlSession.close();
  }

}


