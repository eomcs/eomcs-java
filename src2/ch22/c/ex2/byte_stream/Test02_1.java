// 바이트 데이터를 읽어 primitive data type으로 바꾸기
package ch22.c.ex2.byte_stream;

import java.io.FileInputStream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    FileInputStream in = new FileInputStream("temp/data.bin");

    // 파일에서 4바이트를 읽어 int 변수에 저장하라!
    int value = 0;

    value = value | (in.read() << 24);
    // 0x00000022 <== read()의 리턴 값
    // 0x22000000 <== 24비트 왼쪽으로 이동
    //   0x00000000 <-- value
    // | 0x22000000
    // ------------ 
    //   0x22000000

    value = value | (in.read() << 16);
    // 0x00000033 <== read()의 리턴 값
    // 0x00330000 <== 24비트 왼쪽으로 이동
    //   0x22000000 <-- value
    // | 0x00330000
    // ------------ 
    //   0x22330000

    value = value | (in.read() << 8);
    // 0x00000044 <== read()의 리턴 값
    // 0x00004400 <== 24비트 왼쪽으로 이동
    //   0x22330000 <-- value
    // | 0x00004400
    // ------------ 
    //   0x22334400

    value = value | in.read();
    // 0x00000055 <== read()의 리턴 값
    //   0x22334400 <-- value
    // | 0x00000055
    // ------------ 
    //   0x22334455

    System.out.println(Integer.toHexString(value));

    in.close();
    
    // 파일에서 4바이트 int 값을 읽기 위해 
    // 위 코드처럼 매번 비트 이동 연산을 수행한다면 매우 버거로울 것이다.
    // 유지보수를 좋게 하기 위해 
    // 이런 작업을 수행하는 코드를 별도의 클래스로 캡슐화 하자!
    // => Test02_2.java, DataInputStream 클래스를 참조.

    System.out.println("읽기 완료!");
  }
}








