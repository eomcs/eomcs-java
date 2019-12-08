package ch14.e;

public class Truck extends Car {
  
  int weight; // 짐 싣는 무게 
  
  @Override
  public void run() {
    System.out.println("덜컹덜컹~~ 달린다!");
  }

}
