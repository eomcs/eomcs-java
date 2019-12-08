package ch29.h;

import java.util.Date;

public class Car7 {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  private Date createdDate;
  private BlackBox blackBox;
  
  // 생성자에 의존 객체를 파라미터로 선언하면 
  // 스프링 IoC 컨테이너가 생성자를 호출할 때 
  // 자동으로 해당 객체를 찾아 주입해 준다.
  //@Autowired // 생성자에는 이 애노테이션을 표시하지 않아도 된다.
  public Car7(BlackBox blackBox) {
    System.out.println("Car7(BlackBox)");
    this.blackBox = blackBox;
  }
  
  @Override
  public String toString() {
    return "Car7 [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
        + ", auto=" + auto + ", createdDate=" + createdDate + ", blackBox=" + blackBox + "]";
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
}







