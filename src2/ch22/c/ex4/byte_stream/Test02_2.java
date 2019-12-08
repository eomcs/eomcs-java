// // DataInputStream + 버퍼 기능  = DataInputStream2 
package ch22.c.ex4.byte_stream;

import java.io.FileInputStream;

public class Test02_2 {
  public static void main(String[] args) throws Exception {

    // 파일에서 데이터를 읽는 일을 할 객체를 준비한다.
    FileInputStream other = new FileInputStream("temp/data.bin");
    
    // 위 객체에 버퍼링 기능을 붙인다.
    BufferedInputStream other2 = new BufferedInputStream(other);
    
    // 위 데코레이터 객체에 primitive 타입의 값을 읽는 데코레이터를 붙인다.
    DataInputStream in = new DataInputStream(other2);

    System.out.println("읽기 시작...");
    long start = System.currentTimeMillis();
    
    for (int cnt = 0; cnt < 100000; cnt++) {
      // 바이너리 데이터를 읽을 때는 저장한 순서(파일 포맷)에 맞춰 읽어야 한다.
      short s = in.readShort();
      int i = in.readInt();
      long l = in.readLong();
      String str = in.readUTF();
      boolean b = in.readBoolean();
    }

    long end = System.currentTimeMillis();
    System.out.println(end - start);
    
    in.close();

    System.out.println("읽기 완료!");
  }
}








