// Java I/O API 사용하기 - ObjectInputStream으로 Serialize 데이터를 읽기 
package com.eomcs.io.ex10;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0230 {

  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/test9_3.data");
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // Serialize로 출력된 데이터를 읽어 다시 원래의 객체로 만들기
    Member2 member = (Member2) in.readObject();

    in.close();

    System.out.println(member);
  }

}

// 용어 정리!
// Serialize   : 객체 ===> 바이트 배열   (marshalling 이라고도 부른다.)
// Deserialize : 바이트 배열 ===> 객체   (unmarshalling 이라고도 부른다.)
// 









