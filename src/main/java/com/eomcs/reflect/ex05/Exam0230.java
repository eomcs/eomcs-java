// 목록에 들어 있는 값의 타입을 알아내기
package com.eomcs.reflect.ex05;

import java.util.ArrayList;

public class Exam0230 {

  public static void main(String[] args) throws Exception {
    ArrayList<Object> values = new ArrayList<>();
    values.add(100);
    values.add(100L);
    values.add(3.14f);
    values.add(314.55);
    values.add(true);
    values.add('A');
    values.add("Hello");
    values.add(new int[] {100, 200, 300});
    values.add(new String[] {"aaa", "bbb", "ccc"});

    for (Object value : values) {
      printTypeInfo(value.getClass());
    }
  }

  private static void printTypeInfo(Class<?> type) {
    if (type.getName().startsWith("[")) {
      System.out.printf("=> %s[]\n", type.getComponentType().getName());
    } else {
      System.out.printf("=> %s\n", type.getName());
    }
  }

}





