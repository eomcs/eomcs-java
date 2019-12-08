package ch14.e;

public class DumpTruck extends Truck {
  
  int capacity; // 승차인원
  
  @Override
  public void run() {
    System.out.println("털털털~ 달린다!");
  }

}
