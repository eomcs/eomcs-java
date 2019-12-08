package ch29.e;

import java.util.Properties;

public class Tire2 {
  private String maker;
  private Properties props;
  
  @Override
  public String toString() {
    return "Tire2 [maker=" + maker + ", props=" + props + "]";
  }
  public String getMaker() {
    return maker;
  }
  public void setMaker(String maker) {
    this.maker = maker;
  }
  public Properties getProps() {
    return props;
  }
  public void setProps(Properties props) {
    this.props = props;
  }

}
