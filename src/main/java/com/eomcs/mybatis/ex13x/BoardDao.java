package com.eomcs.mybatis.ex13x;

import java.util.List;

public interface BoardDao {
  List<Board> selectList();
  int insert(Board board);
}









