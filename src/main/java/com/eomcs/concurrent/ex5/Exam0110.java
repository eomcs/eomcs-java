// 비동기 방식으로 인한 문제 및 해결책
package com.eomcs.concurrent.ex5;

public class Exam0110 {

  public static void main(String[] args) {
    Account account = new Account("111-11-1111-111", 100_0000);

    ATM 강남 = new ATM("강남", account);
    ATM 서초 = new ATM("서초", account);
    ATM 부산 = new ATM("부산", account);
    ATM 대전 = new ATM("대전", account);
    ATM 광주 = new ATM("광주", account);

    강남.start();
    서초.start();
    부산.start();
    대전.start();
    광주.start();

    // 강남, 서초 등 여러 개의 스레드가
    // 같은 객체에 대해 메서드를 호출하여 동시에 값을 변경하려 할 때
    // 서로 그 메모리의 값을 덮어쓰는 문제가 발생한다.
    // 이처럼 여러 스레드가 동시에 실행할 때 문제가 되는 코드들을
    // "임계 구역(Critical Section; Critical Region)"이라 부른다.
    // 이 에제에서는 여러 스레드가 동시에 호출하고,
    // 같은 인스턴스의 변수 값을 변경하는 메서드인 "withdraw()"가
    // critical section이다.
    //
    // 해결책?
    // => 한 번에 한 스레드 만이 변수의 값을 변경하도록 접근을 제한하면 된다.
    // 주의!
    // => 동시에 여러 스레드가 같은 메모리에 대해 값을 조회할 떄는 문제가 발생하지 않는다.
    //
    //
    // 세마포어(n); semaphore
    // => 크리티컬 섹션에 진입할 수 있는 스레드의 수를 지정한다.
    // => 자바에서는 세마포어를 지원하지 않는다.
    // => 개발자가 직접 처리해야 한다.
    //
    // 뮤텍스; mutex(mutual exclusion, 상호배제)
    // => 한 번에 오직 한 개의 스레드만이 크리티컬 섹션에 접근할 수 있다.
    // => 예) 선풍기 풍량세기, 라디오 채널, TV 채널 등
    // => semaphore(1)과 같다.
    // => 자바는 synchronized 키워드를 통해 뮤텍스를 사용할 수 있다.
  }
}


// 용어정리!
// 임계 구역(critical section)
// - 여러 스레드가 동시에 실행할 때 문제가 발생하는 코드 블록을 말한다.
// - critical region 이라고도 부른다.
// - 같은 메모리에 여러 스레드가 동시에 접근하여 값을 변경하려 할 때 문제가 발생하는 것이다.
// 즉 다른 스레드가 사용하는 변수의 값을 임의로 변경하면 원래의 의도대로 동작하지 않게 되는 것이다.
// - "스레드 안전(thread safe)하지 않다"라고 말한다.
//
// 스레드 안전(thread safe)
// - 여러 스레드가 동시에 실행하더라도 아무런 문제가 되지 않는 코드를 말한다.
// - 여러 스레드가 같은 메모리에 접근하더라도 읽기만 한다면 아무런 문제가 되지 않는다.
//
// 세마포어(semaphore)
// - critical section에 접근하는 스레드의 수를 제어하는 기법
// - 보통 다음의 형식으로 표시한다.
// semaphore(n)
// n은 개수를 의미한다.
// 예) semaphore(3) : 동시에 3개의 스레드가 접근할 수 있다는 의미다.
// - 자바는 2개 이상의 접근을 허용하지 않는다. 오직 한 개만 가능하다.
//
// 뮤텍스(mutual exclusion; MUTEX)
// - critical section에 오직 한 개의 스레드만이 접근하는 것.
// - semaphore(1) 과 같다.
// - 자바에서 synchronized 는 해당 블록(critical section)을 뮤텍스로 선언하는 것이다.


