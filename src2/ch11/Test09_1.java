// Wrapper 클래스
package ch11;

public class Test09_1 {
  public static void main(String[] args) {
    // 자바는 primitive type의 값을 좀 더 정교하게 제어할 수 있도록 
    // 자바 원시 타입의 각각에 대응하는 클래스를 제공한다.
    // 이 클래스를 "Wrapper 클래스"라 부른다.
    //
    // byte ==> Byte 클래스 (java.lang 패키지 소속이다)
    // short ==> Short 클래스
    // int ==> Integer 클래스
    // long ==> Long 클래스
    // float ==> Float 클래스
    // double ==> Double 클래스
    // boolean ==> Boolean 클래스
    // char ==> Character 클래스 
    //
    
    // wrapper 클래스의 생성자가 deprecated 상태이다. 
    // 가능한 생성자를 사용하여 인스턴스를 생성하지 말라!
    // deprecated(비난받는)? 사용하지 않는 것이 좋다고 결정되었고, 가까운 장래에 제거될 것이라는 의미.
    // 
    //Integer i1 = new Integer(100); // 가능한 사용하지 말자!
    Integer i1 = Integer.valueOf(100); // OK!

    //Character c1 = new Character('가');
    Character c1 = Character.valueOf('가');
    
    // Wrapper 클래스는 해당 값을 다루는 다양한 메서드를 제공한다.
    byte b = i1.byteValue(); // int를 byte로 변환하여 리턴하는 메서드
    String s = i1.toString(); // int를 String 인스턴스로 리턴하는 메서드
    String str = Integer.toHexString(200); // int 값을 16진수로 변환한 후 문자열로 리턴. 
  }
}
















