package com.eomcs.oop.ex09.e;

// 터치 기능이 들어간 새 컴퓨터를 위한 새 규칙을 정의한다.
// => 일단 기존 규칙을 가져오기 위해 상속 받는다.
public interface Computer2 extends Computer {
  
  // 기존 규칙에 새 규칙을 추가한다.
  void touch();
}







