package ch26.i;

import java.sql.Date;

public class Board {
  
  private int no;
  private String title;
  private String contents;
  private Date createdDate;
  private int viewCount;
  
  @Override
  public String toString() {
    return "Board2 [no=" + no + ", title=" + title + ", contents=" + contents + ", createdDate="
        + createdDate + ", viewCount=" + viewCount + "]";
  }
  
  public int getNo() {
    return no;
  }
  public Board setNo(int no) {
    this.no = no;
    return this;
  }
  public String getTitle() {
    return title;
  }
  public Board setTitle(String title) {
    this.title = title;
    return this;
  }
  public String getContents() {
    return contents;
  }
  public Board setContents(String contents) {
    this.contents = contents;
    return this;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public Board setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    return this;
  }
  public int getViewCount() {
    return viewCount;
  }
  public Board setViewCount(int viewCount) {
    this.viewCount = viewCount;
    return this;
  }
  
  
}
