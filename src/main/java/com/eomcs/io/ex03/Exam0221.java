// Character Stream - char[] ==> String 변환하기
package com.eomcs.io.ex03;

import java.io.FileReader;

public class Exam0221 {

  public static void main(String[] args) throws Exception {
    FileReader in = new FileReader("temp/test2.txt");

    char[] buf = new char[100];

    int count = in.read(buf); 
    String str = new String(buf, 0, count);

    in.close();

    System.out.printf("[%s]\n", str);
  }

}




