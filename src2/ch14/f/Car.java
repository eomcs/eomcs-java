package ch14.f;

public abstract class Car {
  String model;
  int cc;
  
  // 서브 클래스마다 달리는 방식이 다를 경우에
  // 굳이 수퍼 클래스에서 고생스럽게 run()을 정의할 필요는 없다.
  // 왜? 서브 클래스에서 오버라이딩 할 것이기 때문에.
  // 
  // 이럴 경우 메서드를 구현하지 말고, 몸체(method body)없는 메서드를 정의하라.
  // => 이런 메서드를 "추상 메서드(abstraction method)"라 부른다.
  // => 문법:  메서드 선언 앞에 abstract 키워드를 붙인다.
  // 
  // 추상메서드는 몸체가 없기 때문에 호출할 수 없다.
  // 따라서 추상 메서드를 갖고 있는 클래스는 인스턴스를 생성하지 못하게 해야 한다.
  // 그래야 인스턴스를 통해 메서드를 호출하지 못할 것 아닌가!
  // 그래서 추상 메서드를 갖고 있는 클래스는 인스턴스를 만들지 못하는 추상 클래스가 되어야 한다.
  // => 문법: 클래스 선언 앞에 abstract를 붙여라. 
  public abstract void run();
  
  public void stop() {
    System.out.println("멈춘다!");
  }
}
