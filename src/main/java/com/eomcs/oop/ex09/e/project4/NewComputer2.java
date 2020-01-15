package com.eomcs.oop.ex09.e.project4;

import com.eomcs.oop.ex09.e.Computer;

// 4번째 프로젝트에서 터치 기능이 들어간 새 컴퓨터를 만들기로 했다.
// 방법2)
//   => 방법1처럼 기존 규칙을 상속 받아서 새 규칙을 만들면,
//      계속 규칙이 늘어나 관리하기가 어려운 문제가 있다.
//      그리고 규칙에 따라 구분하도록 코딩해야 하기 때문에 프로그래밍 하기가 불편한다.
//      기존 규칙에 새 규칙을 추가한다면 이런 문제가 제거된다.
//   => touch 기능을 기존 규칙(Computer2)에 추가한다.
//   => 문제는 인터페이스에 새 규칙을 추가하게 되면 
//      기존에 구현한 클래스는 모두 오류가 발생할 것이다.
//      왜? 
//      규칙이 달라졌기 때문에 클래스 구현 오류가 발생하는 것이다.
//      클래스는 항상 인터페이스의 모든 메서드를 구현해야 하기 때문이다.
// 
public class NewComputer2 implements Computer {
  
  // 기존 규칙을 구현하고,
  public void compute() {
    System.out.println("새 컴퓨터..");
  }

  // 새로 추가한 규칙도 구현한다.
  public void touch() {
    System.out.println("오호라.. 터치가 되네. 이거 서피스 프로인가?");
  }

}






