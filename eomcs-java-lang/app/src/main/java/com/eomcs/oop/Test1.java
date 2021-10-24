package com.eomcs.oop;

public class Test1 {

  public static void main(String[] args) {
    Member m = new Member();
    m.setNo(1);
    m.setName("홍길동");
    m.setEmail("hong@test.com");
    m.setPassword("1111");
    m.setAge(20);
    m.setWork(true);

    System.out.println(m);
  }

  static class Member {
    int no;
    String name;
    String email;
    String password;
    int age;
    boolean work;

    @Override
    public String toString() {
      return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
          + ", age=" + age + ", work=" + work + "]";
    }

    public int getNo() {
      return no;
    }
    public void setNo(int no) {
      this.no = no;
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
    public int getAge() {
      return age;
    }
    public void setAge(int age) {
      this.age = age;
    }
    public boolean isWork() {
      return work;
    }
    public void setWork(boolean work) {
      this.work = work;
    }
  }
}
