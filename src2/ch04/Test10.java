// 논리 연산자: &&, ||, ^, !
package ch04;

public class Test10 {
  public static void main(String[] args) {
    // && (AND) 논리 연산자
    // => 피 연산자 모두 true 일 때만 결과가 true 된다.
    System.out.println(true && true);  // true
    System.out.println(true && false); // false
    
    // || (OR) 논리 연산자
    // => 둘 중 한 개라도 true 이면 결과가 true이다.
    System.out.println(true || false); // true
    System.out.println(false || false); // false
    
    // ^ (exclusive OR) 논리 연산자
    // => 피연산자가 서로 다를 때만 true 이다.
    System.out.println(true ^ true);  // false
    System.out.println(false ^ false); // false
    System.out.println(true ^ false); // true
    
    // &&, || 연산는 피연산자가 반드시 boolean 타입 이어야 한다. 즉 true 또는 false 이어야 한다.
    //System.out.println(1 && 1); // 컴파일 오류!
    
    // !(not) 논리 연산자
    System.out.println(!true);
    System.out.println(!false);
  }
}

















