package ch29.f;

import org.springframework.beans.factory.FactoryBean;

public class BlackBoxFactoryBean implements FactoryBean<BlackBox> {
  
  String maker;
  String model;
  
  public BlackBoxFactoryBean(String maker, String model) {
    this.maker = maker;
    this.model = model;
    System.out.println("BlackBoxFactoryBean(String,String)");
  }
  
  @Override
  public Class<?> getObjectType() {
    return BlackBox.class;
  }
  
  @Override
  public BlackBox getObject() throws Exception {
    System.out.println("BlackBoxFactoryBean.getObject()");
    BlackBox obj = new BlackBox();
    obj.setMaker(this.maker);
    obj.setModel(this.model);
    return obj;
  }
}
