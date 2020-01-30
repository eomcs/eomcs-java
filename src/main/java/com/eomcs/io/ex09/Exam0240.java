// Java I/O API 사용하기 - Serialize의 대상 클래스가 변경된 경우
package com.eomcs.io.ex09;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0240 {

  public static void main(String[] args) throws Exception {
    FileInputStream fileIn = new FileInputStream("temp/test9_3.data");
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // 실행하기 전 해야할 일: 
    // 1) test9_3.data 파일을 읽기 전에 
    //    Member2 클래스에 전화 번호를 저장할 수 있는 인스턴스 변수를 추가한다.
    Member2 member = (Member2) in.readObject();
    // serialize 데이터를 읽어 Member2 인스턴스를 생성하려고 하면 
    // 실행 오류가 발생한다.
    // 이유?
    // => Serialize 데이터를 출력할 때 사용한 Member2 클래스의 데이터 버전과 
    //    Serialize 데이터를 읽을 때 사용한 Member2 클래스의 데이터 버전이 다르기 때문이다.
    //
    // 어, Member2를 작성할 때 버전 번호를 등록한 적이 없는데요?
    // => 클래스를 작성할 때 버전을 지정하지 않으면 
    //    컴파일러가 자동으로 임의의 버전 번호를 지정한다.
    // => private static final long serialVersionUID 변수를 만들어 
    //    임의의 값을 저장한다.
    // 
    // 그러면 모든 클래스가 serialVersionUID라는 변수를 가지고 있나요?
    // => 아니다. java.io.Serializable 인터페이스를 구현한 클래스만이 
    //    해당 변수를 가진다.
    // 
    // 클래스 버전 번호는 개발자가 임의로 관리할 수 있나요?
    // => 명시적으로 변수를 선언하여 값을 지정할 수 있다.
    // => 클래스가 변경되더라도 이 변수의 값이 같으면 실행 오류를 발생시키지 않고 
    //    정상적으로 읽을 수 있다.


    in.close();

    System.out.println(member);
  }

}

// 용어 정리!
// Serialize   : 객체 ===> 바이트 배열   (marshalling 이라고도 부른다.)
// Deserialize : 바이트 배열 ===> 객체   (unmarshalling 이라고도 부른다.)
// 









