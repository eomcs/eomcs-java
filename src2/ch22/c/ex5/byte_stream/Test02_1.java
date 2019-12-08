// DataOutputStream + BufferedOutputStream
package ch22.c.ex5.byte_stream;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    // 실제 파일에 출력을 수행하는 객체를 준비한다.
    FileOutputStream other = new FileOutputStream("temp/data.bin");
    
    // 위 객체에 먼저 버퍼링 기능을 붙인다.
    BufferedOutputStream other2 = new BufferedOutputStream(other);
    
    // 위 데코레이터 객체에 다시 primitive 타입의 값을 출력하는 데코레이터 객체를 붙인다.
    DataOutputStream out = new DataOutputStream(other2);
    
    short s = 0x1122;
    int i = 0x33445566;
    long l = 0x1122334455667788L;
    String str = "ABC가각간";
    boolean b = true;

    System.out.println("출력 시작...");
    long start = System.currentTimeMillis();
    
    for (int cnt = 0; cnt < 100000; cnt++) {
      out.writeShort(s);
      out.writeInt(i);
      out.writeLong(l);
      out.writeUTF(str);
      out.writeBoolean(b);
    }
    
    // 버퍼 기능을 사용할 때는 항상 맨 마지막에 잔여 데이터를 출력해야 한다.
    out.flush();
    
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    
    out.close();

    System.out.println("출력 완료!");
  }
}








