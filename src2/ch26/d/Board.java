package ch26.d;

import java.sql.Date;

public class Board {
  private int board_id;
  private String title;
  private String contents;
  private Date created_date;
  private int view_count;
  
  @Override
  public String toString() {
    return "Board [board_id=" + board_id + ", title=" + title + ", contents=" + contents
        + ", created_date=" + created_date + ", view_count=" + view_count + "]";
  }
  public int getBoard_id() {
    return board_id;
  }
  public void setBoard_id(int board_id) {
    this.board_id = board_id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContents() {
    return contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }
  public Date getCreated_date() {
    return created_date;
  }
  public void setCreated_date(Date created_date) {
    this.created_date = created_date;
  }
  public int getView_count() {
    return view_count;
  }
  public void setView_count(int view_count) {
    this.view_count = view_count;
  }
  
  
}
