// Wrapper 클래스 - 생성자 vs valueOf()
package com.eomcs.corelib.ex02;

public class Exam0211 {
  public static void main(String[] args) {

    // new 명령을 사용하여 Integer 객체를 만들면
    // 무조건 새 인스턴스를 생성한다.
    Integer i1 = new Integer(127);
    Integer i2 = new Integer(127);
    System.out.println(i1 == i2); // false

    // -128 ~ 127 범위의 수를 가지고 valueOf()를 호출하면
    // String 리터럴의 경우처럼 상수 풀에 Integer 객체를 생성한다.
    // 같은 값의 Integer 객체가 여러 개 생성되지 않도록 한다.
    Integer i3 = Integer.valueOf(127);
    Integer i4 = Integer.valueOf(127);
    System.out.println(i3 == i4); // true
    System.out.println(i1 == i3); // false

    Integer x = Integer.valueOf(-128);
    Integer y = Integer.valueOf(-128);
    System.out.println(x == y); // true
    // 이유?
    // -128 ~ 127 범위의 수는 자주 사용되는 수이기 때문이다.


    // 그 범위 외의 수는 무조건 새 인스턴스를 생성한다.
    // => 다루는 숫자가 너무 많기 때문에 무조건 상수 풀에 만들기에는
    //    오히려 메모리 낭비가 심해지기 때문이다.
    // => 상수풀에 생성된 객체는 JVM이 종료되기 전까지 유지된다.
    //    가비지가 되지 않는다.
    // => 그러나 heap에 생성된 객체는 주소를 잃어 버리면 가비지가 되기 때문에
    //    메모리를 좀 더 효율적으로 사용할 수 있다.
    Integer i5 = Integer.valueOf(128);
    Integer i6 = Integer.valueOf(128);
    System.out.println(i5 == i6); // false

    // 결론!
    // => wrapper 객체의 값을 비교할 때 == 연산자를 사용하지 말라!
    // => -128 ~ 127 범위 내의 값이라면 == 연산자를 사용하여 비교할 수도 있지만,
    // 매번 비교할 때 마다 범위의 유효성을 생각하는 것이 번거롭다.
    // => 그냥 equals() 메서드를 사용하여 값을 비교하라!
  }
}


