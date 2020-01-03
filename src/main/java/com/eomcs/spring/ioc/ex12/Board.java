package com.eomcs.spring.ioc.ex12;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {
  private static final long serialVersionUID = 1L;

  int no;
  String title;
  String content;
  Date registeredDate;

  @Override
  public String toString() {
    return "Board [no=" + no + ", title=" + title + ", content=" + content + ", registeredDate=" + registeredDate
        + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }

  // 프로퍼티명 : title
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  // 프로퍼티명 : content
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

  // 프로퍼티명 : registeredDate
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
