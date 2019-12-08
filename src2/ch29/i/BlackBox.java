package ch29.i;

import org.springframework.stereotype.Component;

//@Component("okok")

// 빈의 이름을 지정하지 않으면 클래스 이름을 빈의 이름으로 사용한다.
// 단 첫 알파벳은 소문자로 한다.
// 패키지 이름은 포함하지 않는다.
// XML 선언으로 빈을 생성할 때 이름을 지정하지 않으면 
// "패키지명.클래스명#번호" 형태로 이름이 지어진다.
// 그러나 @Component 애노테이션을 사용할 때는 
// 클래스명만 빈의 이름으로 사용된다. 
@Component
public class BlackBox {
  private String maker;
  private String model;
  
  public BlackBox() {
    System.out.println("BlackBox()");
  }
  
  @Override
  public String toString() {
    return "BlackBox [maker=" + maker + ", model=" + model + "]";
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
  
  
}
