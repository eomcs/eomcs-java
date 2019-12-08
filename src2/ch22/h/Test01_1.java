// 파일 입출력과 예외처리 - 예외 처리 전
package ch22.h;

import java.io.FileOutputStream;

public class Test01_1 {
  public static void main(String[] args) throws Exception {
    FileOutputStream out = new FileOutputStream("temp3/data.bin");

    out.write(0x55);
    
    out.close();

    System.out.println("출력 완료!");
  }
}








