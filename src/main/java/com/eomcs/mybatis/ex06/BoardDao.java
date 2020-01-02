package com.eomcs.mybatis.ex06;

import java.util.List;

public interface BoardDao {
  List<Board> selectList();
  int insert(Board board);
}









