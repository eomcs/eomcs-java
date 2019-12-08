package ch29.h;

import java.util.Date;
import javax.annotation.Resource;

public class Car6 {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  private Date createdDate;
  
  // @Autowired + @Qualifier = @Resource
  //@Autowired(required=false)
  //@Qualifier("bb2")
  
  //OpenJDK에서는 @Resource 라이브러리가 포함되어 있지 않다. 
  //별도로 라이브러리를 추가해야 한다.
  //=> mvnrepository.com 에서 javax.annotation 검색하라.
  //=> build.gradle에 추가하라.
  //=> '$ eclipse gradle' 실행하라.
  //=> 이클립스에서 프로젝트를 갱신하라.
  @Resource(name="bb2")   
  private BlackBox blackBox;
  
  public Car6() {
    System.out.println("Car6()");
  }
  
  @Override
  public String toString() {
    return "Car6 [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
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







