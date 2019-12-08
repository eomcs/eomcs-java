package ch26.h;

import java.sql.Date;
import java.util.List;

public class Board {
  
  private int no;
  private String title;
  private String contents;
  private Date createdDate;
  private int viewCount;
  
  // 자식 테이블(x_board_file)의 데이터를 여러 개 받을 필드를 선언한다.
  private List<AttachFile> attachFiles;
  
  // 만약 자식 테이블의 데이터를 한 개만 받는다면 다음과 같이 선언하면 된다.
  //private AttachFile attachFile;
  
  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", contents=" + contents + ", createdDate="
        + createdDate + ", viewCount=" + viewCount + "]";
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
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
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  public List<AttachFile> getAttachFiles() {
    return attachFiles;
  }
  public void setAttachFiles(List<AttachFile> attachFiles) {
    this.attachFiles = attachFiles;
  }
  
}
