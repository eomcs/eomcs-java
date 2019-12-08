package ch24.d;

public class ATM extends Thread {
  
  Account account;
  
  public ATM(String name, Account account) {
    super(name);
    this.account = account;
  }
  
  public void run() {
    double sum = 0;
    while (true) {
      double money = account.withdraw(100);
      
      if (money == 0)
        break;
      
      sum += money;
    }
    System.out.printf("%s 에서 찾은 금액 합계: %.2f\n", this.getName(), sum);
  }
}







