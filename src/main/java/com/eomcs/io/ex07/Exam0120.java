// 메모리에서 데이터를 읽기 - 읽은 데이터를 Member 객체에 저장한다.
package com.eomcs.io.ex07;

import java.io.ByteArrayInputStream;

public class Exam0120 {

  public static void main(String[] args) throws Exception {
    byte[] buf = {0x0b, 0x41, 0x42, 
        (byte)0xea, (byte)0xb0, (byte)0x80, 
        (byte)0xea, (byte)0xb0, (byte)0x81, 
        (byte)0xea, (byte)0xb0, (byte)0x84, 
        0x00, 0x00, 0x00, 0x1b, 0x01};

    ByteArrayInputStream in = new ByteArrayInputStream(buf);

    Member member = new Member();

    // 바이트 배열의 값을 읽어서 String이나 int, boolean 값으로 만들려면 
    // 다음과 같이 비트이동 연산 등을 수행해야 한다.
    // 매번 이런 식으로 코딩을 해야 한다면 
    // 읽어들일 항목이 많을 경우 코드가 매우 복잡할 것이다.
    // 해결책? ByteArrayInputStream을 상속 받아 이런 기능을 수행하는 
    // 메서드를 추가하라! DataInputStream 클래스처럼 만들라!

    // 문자열 바이트의 크기 
    int size = in.read();

    // 문자열 바이트 읽기
    byte[] bytes = new byte[size];
    in.read(bytes);

    member.name = new String(bytes, "UTF-8"); 

    member.age = in.read() << 24;
    member.age += in.read() << 16;
    member.age += in.read() << 8;
    member.age += in.read();

    if (in.read() == 1) 
      member.gender = true;
    else 
      member.gender = false;

    in.close();

    System.out.println(member);

  }

}
