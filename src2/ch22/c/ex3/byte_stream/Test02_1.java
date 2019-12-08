// DataOutputStream + 버퍼 기능  = DataOutputStream2 
package ch22.c.ex3.byte_stream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    BufferedDataOutputStream out = new BufferedDataOutputStream("temp/data.bin");

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








