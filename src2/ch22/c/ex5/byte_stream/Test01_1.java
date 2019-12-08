// Java에서 제공하는 데코레이터 사용하기 : 버퍼없이 대량의 primitive 타입의 값을 출력하기 
package ch22.c.ex5.byte_stream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test01_1 {
  public static void main(String[] args) throws Exception {
    // 실제 파일에 출력을 수행하는 객체를 준비한다.
    FileOutputStream other = new FileOutputStream("temp/data.bin");
    
    // 파일을 출력하는 객체에 primitive 타입의 값을 출력하는 데코레이터 객체를 붙인다.
    DataOutputStream out = new DataOutputStream(other);

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
    
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    
    out.close();

    System.out.println("출력 완료!");
  }
}








