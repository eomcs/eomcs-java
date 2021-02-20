// Serialize - 인스턴스 필드의 값 읽기 : getter/setter 없는 경우
package com.eomcs.io.ex12.c;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Test1_2 {
  public static void main(String[] args) throws Exception {

    FileInputStream in0 = new FileInputStream("temp/score.data");
    ObjectInputStream in = new ObjectInputStream(in0);

    Score1 score = (Score1) in.readObject();
    System.out.println(score);

    // getter/setter 없어도 필드 값을 읽는데 문제 없다.

    in.close();
  }
}
