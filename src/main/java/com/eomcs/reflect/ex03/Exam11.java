// 필드 정보 추출
package com.eomcs.reflect.ex03;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Exam11 {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    Class<?> clazz = Car.class;

    Field[] fields = clazz.getDeclaredFields();
    for (Field f : fields) {
      System.out.printf("%s:%s\n", f.getName(), f.getType().getName());
    }

    Field makerField = clazz.getDeclaredField("maker");

    Constructor<Car> defaultConst = (Constructor<Car>) clazz.getConstructor();
    Car car = defaultConst.newInstance();

    // private modifier로 선언된 필드라 하더라도
    // 다음 메서드를 통해 접근 가능하도록 만들 수 있다.
    makerField.setAccessible(true);

    // private 필드에 값 넣기
    // 가능해?
    makerField.set(car, "비트자동차");

    System.out.println(car);
  }

}


