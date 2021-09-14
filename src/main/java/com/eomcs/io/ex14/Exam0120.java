// 파일 입출력과 예외처리 - 예외 처리 후
package com.eomcs.io.ex14;

import java.io.FileOutputStream;
import java.io.IOException;

public class Exam0120 {
  public static void main(String[] args) {

    FileOutputStream out = null;

    try {
      out = new FileOutputStream("temp3/data.bin");
      out.write(0x55);

    } catch (IOException e) {
      System.out.println("파일 입출력 예외 발생!");
      //      e.printStackTrace();

    } finally {
      try {out.close();} catch (Exception e2) {}

    }

    System.out.println("출력 완료!");
  }
}








