package ch12.d;

public class Patient {
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  
  String name;
  int age;
  int height;
  int weight;
  int gender;
  
  public String toString() {
    return String.format("name=%s, age=%d, height=%d, weight=%d, gender=%d", 
        this.name, this.age, this.height, this.weight, this.gender);
  }
}







