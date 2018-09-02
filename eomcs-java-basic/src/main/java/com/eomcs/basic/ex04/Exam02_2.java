// 키보드 입력 받기 - 응용 
package com.eomcs.basic.ex04;

public class Exam02_2 {
    public static void main(String[] args) {
        // 키보드 정보를 가져온다.
        // 키보드에서 값을 꺼내주는 도구를 연결한다.
        java.io.InputStream keyboard = System.in;
        java.util.Scanner keyScan = new java.util.Scanner(keyboard);
        
        // 팀 멤버의 정보를 입력 받아 출력하라.
        // 이름, 전화, 이메일, 나이, 재직여부
        // 예)
        // 이름? 홍길동
        // 홍길동
        // 전화? 1111-2222
        // 1111-2222
        // 이메일? hong@test.com
        // hong@test.com
        // 나이? 20
        // 20
        // 재직여부? y
        // y
        System.out.print("이름? ");
        System.out.println(keyScan.nextLine());
        System.out.print("전화? ");
        System.out.println(keyScan.nextLine());
        System.out.print("이메일? ");
        System.out.println(keyScan.nextLine());
        System.out.print("나이? ");
        System.out.println(keyScan.nextLine());
        System.out.print("재직여부? ");
        System.out.println(keyScan.nextLine());

    }
}

