package ch29.a;

import org.springframework.stereotype.Component;

// 스프링 IoC 컨테이너에게 이 클래스의 객체를 자동으로 생성하라고 요구한다.
@Component
public class Student2 {
  private String name;
  private String email;
  private String password;
  private String tel;
  private int age;
  private boolean working;
  
  @Override
  public String toString() {
    return "Student2 [name=" + name + ", email=" + email + ", password=" + password + ", tel=" + tel
        + ", age=" + age + ", working=" + working + "]";
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
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public boolean isWorking() {
    return working;
  }
  public void setWorking(boolean working) {
    this.working = working;
  }
  
  
}
