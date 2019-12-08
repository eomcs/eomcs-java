package ch29.e;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Car3 {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  private Date createdDate;
  private BlackBox blackBox;
  private List<Tire> tires;
  private Map<String,Object> options;
  
  
  @Override
  public String toString() {
    return "Car3 [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
        + ", auto=" + auto + ", createdDate=" + createdDate + ", blackBox=" + blackBox + ", tires="
        + tires + ", options=" + options + "]";
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public int getCc() {
    return cc;
  }
  public void setCc(int cc) {
    this.cc = cc;
  }
  public int getValve() {
    return valve;
  }
  public void setValve(int valve) {
    this.valve = valve;
  }
  public boolean isAuto() {
    return auto;
  }
  public void setAuto(boolean auto) {
    this.auto = auto;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public BlackBox getBlackBox() {
    return blackBox;
  }
  public void setBlackBox(BlackBox blackBox) {
    this.blackBox = blackBox;
  }
  public List<Tire> getTires() {
    return tires;
  }
  public void setTires(List<Tire> tires) {
    this.tires = tires;
  }
  public Map<String, Object> getOptions() {
    return options;
  }
  public void setOptions(Map<String, Object> options) {
    this.options = options;
  }
  
}
