// primitive data type의 값을 출력하기
package ch22.c.ex2.byte_stream;

import java.io.FileOutputStream;

public class Test01_1 {
  public static void main(String[] args) throws Exception {

    FileOutputStream out = new FileOutputStream("temp/data.bin");

    int value = 0x22334455;

    // 위의 4바이트 value 값을 출력하시오!
    // => 상위 바이트를 출력하고 싶으면 맨 뒤로 이동시켜야 한다.
    // => >> 연산자를 사용하여 비트를 이동시킨다.
    // => 빈자리는 부호 비트로 채워진다. 양수이면 0, 음수이면 1로 채운다.
    // => 맨 끝에서 넘어간 바이트는 버려진다.
    out.write(value >> 24); // 00 00 00 22 | 33 44 55 
    out.write(value >> 16); // 00 00 22 33 | 44 55
    out.write(value >> 8);  // 00 22 33 44 | 55
    out.write(value);       // 22 33 44 55 

    // primitive 타입의 값을 출력하기 위해 
    // 매번 위 코드처럼 작성하는 것은 번거로운 일이다.
    // 이 코드를 캡슐화 하여 별도의 클래스로 정의해보자!
    // => Test01_2.java, DataOutputStream.java 참조!
    
    out.close();

    System.out.println("출력 완료!");
  }
}








