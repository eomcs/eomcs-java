package com.eomcs.openapi.json;

public class Manager extends Member {
  String position;
  String fax;

  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getFax() {
    return fax;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
}