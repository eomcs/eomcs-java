// 바이너리 데이터 입출력 - FileOutputStream - 배열의 특정 위치의 값들을 출력하기
// 
package ch22.b.byte_stream;

import java.io.FileOutputStream;

public class Test03_1 {
  public static void main(String[] args) throws Exception {
    // 배열 단위로 출력하기
    // 주의!
    // => 기존의 파일을 덮어쓴다.
    FileOutputStream out = new FileOutputStream("data.bin");

    byte[] bytes = {0x11, 0x22, 0x33, 0x44, 0x55, 0x66};

    // write(byte[]) : 배열의 값 전체를 출력한다.
    // write(byte[], 시작인덱스, 출력개수) : 시작 위치부터 지정된 개수를 출력한다.
    //
    out.write(bytes, 2, 3); // 배열의 일부만 출력한다.

    out.close();

    System.out.println("출력 완료!");
  }
}








