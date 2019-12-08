package ch14.e;

public class PickupTruck extends Truck {
  
  boolean dump; 
  
  @Override
  public void run() {
    System.out.println("꽈당당~ 달린다!");
  }

}
