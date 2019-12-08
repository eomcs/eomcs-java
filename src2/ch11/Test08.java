// StringBuffer 클래스 사용법 - equals()
package ch11;

public class Test08 {
  public static void main(String[] args) {
    StringBuffer b1 = new StringBuffer("Hello");
    StringBuffer b2 = new StringBuffer("Hello");
    
    System.out.println(b1 == b2);
    
    // StringBuffer의 equals()는 값을 비교하는 것이 아니라 인스턴스가 같은지 비교한다.
    // 즉 == 연산자와 똑 같다.
    System.out.println(b1.equals(b2)); 
    
    // 어떤 클래스에 equals()가 있나요?
    // => 모든 클래스에 있다.
    //
    // 엥?
    // => 자바의 모든 클래스는 Object 클래스에 정의된 기능을 상속 받는다.
    //    Object를 상속 받지 않을 수 없다.
    // => 따라서 자바의 모든 클래스는 Object 클래스에 정의된 메서드를 사용할 수 있다.
    //
    // Object 클래스에 어떤 메서드가 있나요?
    // => equals() : 인스턴스가 같은지를 비교한다.
    // => toString() : 인스턴스의 클래스명과 해시코드를 리턴한다.
    // => hashCode() : 인스턴스의 해시 코드를 리턴한다.
    // => getClass() : 클래스 정보를 리턴한다.
    // => clone() : 인스턴스를 복제하여 리턴한다.
    // => finalize() : 가비지 컬렉터에 의해 제거되기 전에 호출된다.
    // => wait() : 잠시 실행을 멈춘다. 외부에서 깨워줄 때까지 멈춘다.
    // => notify() : wait()로 실행을 멈춘 것을 깨운다.
    // => notifyAll() : wait()로 실행을 멈춘 것을 모두 깨운다.
    
    // Object로부터 상속 받은 equals()가 원래는 인스턴스가 같은지를 비교한다고 했는데,
    // 왜, String 클래스는 인스턴스가 아닌 문자열 값을 비교하나요?
    // => String 클래스는 상속 받은 equals() 메서드를 재정의하였기 때문이다.
    // => 이렇게 상속 받은 기능을 자신의 역할에 맞게끔 재정의하는 것을 "오버라이딩(overriding)"이라 한다.
    //
    // StringBuffer는 재정의 하지 않아서 다르다고 결과가 나온건가요?
    // => 예!
  }
}
















