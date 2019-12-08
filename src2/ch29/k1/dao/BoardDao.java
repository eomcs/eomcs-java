package ch29.k1.dao;

import java.util.List;
import ch29.k1.vo.Board;

public interface BoardDao {
  int insert(Board board);
  List<Board> findAll();
  Board findByNo(int no);
  int increaseCount(int no);
  int update(Board board);
  int delete(int no);
}







