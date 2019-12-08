package ch27.c;

public class Student {
  private String name;
  private String email;
  private boolean working;
  private int age;
  
  public Student() {
    System.out.println("Student()");
  }
  
  public Student(String name, String email) {
    System.out.println("Student(String,String)");
    this.name = name;
    this.email = email;
  }
  
  public Student(String name, String email, boolean working, int age) {
    System.out.println("Student(String,String,boolean,int)");
    this.name = name;
    this.email = email;
    this.working = working;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Student [name=" + name + ", email=" + email + ", working=" + working + ", age=" + age
        + "]";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isWorking() {
    return working;
  }

  public void setWorking(boolean working) {
    this.working = working;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
  
  public static String greeting(String name, int age, boolean working) {
    return String.format("%s(%d,%b)님 반갑습니다!", name, age, working);
  }
  
}




