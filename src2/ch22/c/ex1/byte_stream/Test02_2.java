// 데이터 출력에서 버퍼 사용 - 사용 후
package ch22.c.ex1.byte_stream;

import java.io.FileOutputStream;

public class Test02_2 {
  public static void main(String[] args) throws Exception {

    FileOutputStream out = new FileOutputStream("temp/data.bin");

    System.out.println("데이터 쓰는 중...");

    long start = System.currentTimeMillis();

    byte[] buf = new byte[1024];
    int size = 0;

    for (int i = 0; i < 1000000; i++) {
      // 일단 바이트 버퍼에 저장한다.
      buf[size++] = 0x55;

      if (size >= 1024) {
        out.write(buf); // 버퍼가 꽉 차면 파일로 내보낸다.
        size = 0; // 다시 버퍼를 쓸 수 있도록 size를 0으로 초기화 한다. 
      }
    }

    // 마지막으로 버퍼에 남아 있는 바이트를 출력한다.
    out.write(buf, 0, size);

    long end = System.currentTimeMillis();

    System.out.println(end - start);

    out.close();

    System.out.println("출력 완료!");
  }
}








