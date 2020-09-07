// static nested class 문법을 이용하여 상수를 효과적으로 관리하기
package com.eomcs.oop.ex11.z.step7;

public class Category {

  // static nested class 를 사용하여
  // 여러 상수를 그룹 별로 묶어 관리한다.
  // 이점:
  // => 다음 클래스를 보면 상수를 묶어서 관리하는 용도로 만들었다.
  // => 클래스의 크기도 작다.
  // => 이런 클래스를 별도의 파일로 분리하면 여러 개의 파일이 생겨서 오히려 관리하기 번거롭다.
  // 즉 자잘한 클래스들이 여러 파일에 분산되기 때문에 관리하기 번거로워진다.
  // => 차라리 이렇게 중첩 클래스로 만들면 사용할 때 다음과 같이 계층적으로 작성하기 때문에
  // 이해하기가 쉬워진다.
  // int value = Category.computer.CPU;
  // => 바깥 클래스를 패키지처럼 생각할 수 있어 이해하고 관리하기 편하다.
  //

  // 중첩 클래스 이름을 일부로 소문자로 지어서
  // 마치 일반 필드인것처럼 보이게 한다.
  //
  public static class computer {
    public static final int CPU = 1;
    public static final int VGA = 2;
    public static final int RAM = 3;
    public static final int MOUSE = 4;
    public static final int KEYBOARD = 5;
  }

  public static class appliance {
    public static final int TV = 10;
    public static final int AUDIO = 11;
    public static final int DVD = 12;
    public static final int VACUUMCLEANER = 13;
  }

  public static class book {
    public static final int POET = 100;
    public static final int NOVEL = 101;
    public static final int ESSAY = 102;
    public static final int IT = 103;
    public static final int LANG = 104;
  }
}
