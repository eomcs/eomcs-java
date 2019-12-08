package ch29.c;

public class Car {
  private String maker;
  private String model;
  private int cc;
  private int valve;
  private boolean auto;
  
  public Car(int valve, String maker) {
    System.out.println("ch29.c.Car(int,String)");
    this.maker = maker;
    this.valve = valve;
  }

  public Car(String model, int cc) {
    System.out.println("ch29.c.Car(String,int)");
    this.model = model;
    this.cc = cc;
  }

  public Car(String maker, String model) {
    System.out.println("ch29.c.Car(String,String)");
    this.maker = maker;
    this.model = model;
  }
  
  public Car(String maker, String model, int cc, int valve, boolean auto) {
    System.out.println("ch29.c.Car(String,String,int,int,boolean)");
    this.maker = maker;
    this.model = model;
    this.cc = cc;
    this.valve = valve;
    this.auto = auto;
  }
  
  @Override
  public String toString() {
    return "ch29.c.Car [maker=" + maker + ", model=" + model + ", cc=" + cc + ", valve=" + valve
        + ", auto=" + auto + "]";
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
  
  
}
