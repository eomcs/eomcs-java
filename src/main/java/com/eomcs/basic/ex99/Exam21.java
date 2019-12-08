package com.eomcs.basic.ex99;

//# 키보드 입력 받기
//

public class Exam21 {
    public static void main(String[] args) {
        // 키보드 정보를 가져온다.
        java.io.InputStream keyboard = System.in;

        // 키보드에서 값을 꺼내주는 도구를 연결한다.
        java.util.Scanner keyScan = new java.util.Scanner(keyboard);
        
        // print()는 문자열을 출력한 후 줄 바꿈을 하지 않는다.
        System.out.print("팀명? "); 
        
        // nextLine()
        // - Scanner 도구를 사용하여 키보드로부터 한 줄의 문자열을 
        //   가져올 때 사용하는 명령이다.
        // - 동작은? 
        //   사용자가 한 줄 입력할 때까지,
        //   즉 입력 데이터에 줄바꿈을 의미하는 0d0a 2바이트가 들어올 때까지
        //   대기한다.
        //   사용자가 엔터키를 누르면, 
        //   입력값으로 0d0a 2바이트 값이 들어오고,
        //   nextLine() 비로서 사용자가 입력한 값을 리턴한다.
        // - 그 리턴 값을 println() 명령을 사용하여 콘솔로 출력한다.
        System.out.println(keyScan.nextLine());

        keyScan.close();
    }
}

// System.out
// - 표준 출력 장치.
// - 즉 콘솔(모니터, 명령창)을 가리킨다.
// System.in
// - 표준 입력 장치.
// - 즉 키보드를 가리킨다.
