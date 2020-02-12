package com.eomcs.concurrent.ex5;

public class ATM extends Thread {
  Account account;

  public ATM(String name, Account account) {
    super(name);
    this.account = account;
  }

  @Override
  public void run() {
    long money = 0;
    long sum = 0;

    while (true) {
      money = account.withdraw(100);
      if (money <= 0)
        break;
      sum += money;
    }
    System.out.printf("%s에서 찾은 돈: %d원\n", this.getName(), sum);
  }
}


