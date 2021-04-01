package com.eomcs.mybatis.ex14x;

import java.util.List;

public interface BoardDao {
  List<Board> selectList();
  int insert(Board board);
}









