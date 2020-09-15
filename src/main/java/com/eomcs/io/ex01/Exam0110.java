// 폴더 정보 조회 - java.io.File 클래스
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // File 클래스
    // => 파일이나 디렉토리 정보를 관리
    // => 파일이나 디렉토리를 생성,삭제,변경

    // 현재 폴더 정보 조회
    // - "." 으로 경로를 표시한다.
    // - 이클립스에서 프로그램을 실행한다면 . 은 프로젝트 폴더를 가리킨다.
    // - 콘솔에서 프로그램을 실행한다면 . 은 현재 명령어를 실행하는 위치를 가리킨다.
    // - 상위 경로는 ".."으로 표시한다.
    //
    File currentDir = new File("./src/main/java");
    System.out.printf("폴더명: %s\n", currentDir.getName());
    System.out.printf("경로: %s\n", currentDir.getPath());
    System.out.printf("절대경로: %s\n", currentDir.getAbsolutePath());
    System.out.printf("계산된 절대경로: %s\n", currentDir.getCanonicalPath());

    // 존재하지 않는 폴더인 경우 크기를 알아낼 수 없다.
    System.out.printf("총크기: %d\n", currentDir.getTotalSpace());
    System.out.printf("남은크기: %d\n", currentDir.getFreeSpace());
    System.out.printf("가용크기: %d\n", currentDir.getUsableSpace());

    // 존재하지 않는 폴더인 경우 정보를 알아낼 수 없다. 모두 false
    System.out.printf("디렉토리여부: %b\n", currentDir.isDirectory());
    System.out.printf("파일여부: %b\n", currentDir.isFile());
    System.out.printf("감춤폴더: %b\n", currentDir.isHidden());
    System.out.printf("존재여부: %b\n", currentDir.exists());
    System.out.printf("실행가능여부: %b\n", currentDir.canExecute());
  }

}


