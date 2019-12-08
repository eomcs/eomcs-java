// 문자 데이터 출력하기
package ch22.b.character_stream;

import java.io.FileWriter;

public class Test01_1 {

  public static void main(String[] args) throws Exception {

    // FileWriter
    // => 문자 단위로 데이터를 출력하는 일을 한다.
    //
    FileWriter out = new FileWriter("data.txt");

    // write(문자코드) 
    // => JVM에서 문자를 다루기 위해 사용하는 UTF-16 코드를 
    //    OS 기본 문자집합 코드로 변환하여 출력한다.
    out.write('A'); // 0x0041 => 0x41 1바이트 출력
    out.write('B'); // 0x0042 => 0x42 1바이트 출력
    out.write('C'); // 0x0043 => 0x43 1바이트 출력
    out.write('가'); // 0xac00 => 0xEAB080 3바이트 출력 
    out.write('각'); // 0xac01 => 0xEAB081 3바이트 출력 
    out.write('간'); // 0xac04 => 0xEAB084 3바이트 출력

    // character stream 클래스 FileWriter는 문자 데이터를 출력할 때  
    // 자바 내부에서 문자열을 다룰 때 사용하는 UTF-16 코드를 
    // OS의 기본 character set 코드로 값을 변환하여 출력한다.
    // 
    // Linux, macOS 의 기본 character set => UTF-8
    // Windows 의 기본 character set => MS-949
    // 
    // 따라서 같은 프로그램을 어느 OS에서 실행하느냐에 따라 
    // 출력되는 코드 값이 달라진다.
    // 만약 이클립스에서 실행한다면 무조건 UTF-8로 인코딩(코드 값으로 변환) 되어 출력된다.
    // 명령창(콘솔창)에서 실행한다면 위에서 설명한대로 OS에 따라 다르게 인코딩 된다.
    // 
    // OS에 상관없이 동일한 character set으로 출력하고 싶다면 
    // JVM을 실행할 때 file.encoding 프로퍼티에 character set 이름을 지정하라.
    //    예) java -Dfile.encoding=UTF-8 클래스명
    // 단, character set을 지정할 때는 해당 OS에서 사용가능한 문자표이어야 한다.
    // MS Windows에서는 ms949 문자표를 사용할 수 있지만,
    // 리눅스나 macOS에서는 ms949 문자표를 사용할 수 없다.
    // 왜? 국제 표준이 아니기 때문이다.
    // 결론!
    // => 무슨 OS를 사용하든지 출력할 때는 가능한 국제 표준인 UTF-8을 사용하라.
    // 
    
    out.close();
    
    System.out.println("출력 완료!");
  }

}
