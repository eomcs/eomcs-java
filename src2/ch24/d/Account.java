package ch24.d;

public class Account {
  String name;
  double balance;
  
  public Account(String name, double balance) {
    this.name = name;
    this.balance = balance;
  }

  synchronized public double withdraw(double money) {
    // 1) 이 메서드처럼 여러 스레드가 같은 메모리(balance 필드)의 값을 동시에 변경할 때 
    // 문제가 발생하는 코드를 "크리티컬 섹션(critical section) 또는 크리티컬 리전(critical region)"
    // 이라 부른다.
    // 2) 크리티컬 섹션에 동시에 접근하지 못하게 하는 기법을 
    // "뮤텍스(mutex)" 또는 "세마포어(1)(semaphore)"라 부른다.
    // 3) 자바에서 뮤텍스를 구현하는 방법
    // => 크리티컬 섹션에 해당하는 메서드나 코드 블록에 sychronized 키워드를 붙여 
    //    한 번에 한 스레드만 진입할 수 있도록 lock을 건다.
    // 
    double temp = this.balance;
    
    if (temp - money < 0)
      return 0;
    
    temp = temp - money;
    
    this.balance = temp;
    
    return money;
  }
  
  
}
