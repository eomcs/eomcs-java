// // DataInputStream + 버퍼 기능  = DataInputStream2 
package ch22.c.ex3.byte_stream;

public class Test02_2 {
  public static void main(String[] args) throws Exception {

    BufferedDataInputStream in = new BufferedDataInputStream("temp/data.bin");

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








