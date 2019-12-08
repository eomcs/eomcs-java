// Stack 클래스 사용법 - empty()
package ch20.b;

import java.util.Stack;

public class Test02 {

  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    
    // push() - 스택의 맨 마지막에 값을 추가한다.
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");
    
    while (!stack.empty()) {
      System.out.println(stack.pop());
    }
  }

}

