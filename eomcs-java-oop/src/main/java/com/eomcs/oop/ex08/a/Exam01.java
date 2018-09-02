// 상속 - Generalization 수행 전
package com.eomcs.oop.ex08.a;

// 상속의 종류
// 1) specialization
//    => 가장 많이 사용하는 방법으로 수퍼 클래스를 상속 받아 서브 클래스를 만드는 것이다.
//    => 수퍼클래스에 특징을 추가하여 더 특별한 기능을 수행하는 서브클래스를 만든다.
//       그래서 이런 상속을 "특수화/전문화(specialization)"이라 부른다.
// 2) generalization
//    => 리팩토링 과정에 수행하는 방법이다.
//    => 서브클래스들의 공통 분모를 추출하여 수퍼클래스를 정의하는 방법을 말한다.
//    => 그래서 이런 상속을 "일반화/표준화(generalization)"이라 부른다.
//
// Sedan 클래스와 Truck 클래스의 공통 분모를 추출하여 
// Car라는 클래스를 정의하고,
// 두 클래스는 이렇게 새로 만든 Car 클래스를 상속 받도록 한다.
public class Exam01 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
