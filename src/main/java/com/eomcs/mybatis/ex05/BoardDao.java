package com.eomcs.mybatis.ex05;

import java.util.List;

public interface BoardDao {
  List<Board> selectList();
  int insert(Board board);
}









