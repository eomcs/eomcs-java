// 데이터 출력에서 버퍼 사용 - 버퍼 기능을 대신 수행해주는 BufferedOutputStream 사용하기
package ch22.c.ex1.byte_stream;

public class Test02_3 {
  public static void main(String[] args) throws Exception {

    BufferedOutputStream out = new BufferedOutputStream("temp/data.bin");

    System.out.println("데이터 쓰는 중...");

    long start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i++) {
      out.write(0x55);
      // BufferedOutputStream은 내부적으로 1바이트를 파일로 바로 출력하지 않고
      // 일단 byte[] 배열에 저장한다.
      // 바이트 배열이 꽉 찼을 때 파일로 출력할 것이다.
      // 그래서 Test01_1.java 에서와 같이 1바이트씩 출력하는 것은 같지만,
      // 속도는 이 클래스가 더 빠르다.
    }

    // BufferedOutputStream은 바이트 배열이 꽉 찼을 때만 파일로 출력하기 때문에 
    // 바이트 배열에 데이터가 남아 있을 경우 강제적으로 출력해야 한다. 
    out.flush(); // 버퍼에 남아 있는 것을 방출한다.

    long end = System.currentTimeMillis();

    System.out.println(end - start);

    out.close();

    System.out.println("출력 완료!");
  }
}








