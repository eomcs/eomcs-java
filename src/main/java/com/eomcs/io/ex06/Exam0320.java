// BufferedInputStream과 BufferedOutputStream을 사용하여 파일 복사 및 시간 측정
package com.eomcs.io.ex06;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    BufferedInputStream in = new BufferedInputStream("temp/jls8.pdf");
    BufferedOutputStream out = new BufferedOutputStream("temp/jls8_4.pdf");

    int b;

    long startTime = System.currentTimeMillis(); // 밀리초

    while ((b = in.read()) != -1) 
      out.write(b);

    long endTime = System.currentTimeMillis();

    System.out.println(endTime - startTime);

    in.close();
    out.close();
  }

}
