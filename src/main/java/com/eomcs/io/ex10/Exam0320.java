// Java I/O API 사용하기 - Serialize의 대상 클래스가 변경된 경우
package com.eomcs.io.ex10;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/test9_4.data");
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // 실행하기 전 해야할 일: 
    // 1) test9_4.data 파일을 읽기 전에 
    //    Member3 클래스에 전화 번호를 저장할 수 있는 인스턴스 변수를 추가한다.
    Member3 member = (Member3) in.readObject();
    // Exam02_4.java의 경우와 달리 
    // Member3 객체의 시리얼 데이터를 출력한 다음, 
    // Member3를 변경하더라도 데이터를 읽는데 실행 오류가 발생하지 않았다.
    // 이유?
    // => 클래스 버전 번호가 같기 때문이다.
    // => 클래스가 변경되더라도 클래스의 serialVersionUID의 값이 같다면 
    //    같은 클래스로 간주한다.
    //    그래서 readObject()를 호출하여 시리얼 데이터를 읽을 때 오류가 발생하지 않는다.
    // 
    // serialVersionUID의 사용법
    // => 클래스를 변경하더라도 기존 데이터를 읽는데 문제가 없다면 
    //    변경한 클래스의 버전을 이전 버전과 같게 하라!
    // => 그러나 클래스의 변경 사항이 너무 많아 이전 데이터를 읽기가 부적합하다면 
    //    변경한 클래스의 버전을 바꿔 읽을 때 오류가 나게 하라!
    // => Member3의 경우 클래스를 변경할 때 tel 변수를 추가하였다.
    //    따라서 이전 데이터를 읽는데는 문제가 없다.
    //    그래서 버전 번호를 바꾸지 않은 것이다. 

    in.close();

    System.out.println(member);
  }

}










