package ch30.d;

// 지정된 객체의 메서드를 호출할 때,
// 메서드 호출 전이나 후에 어떤 작업을 수행하는 일을 한다.
public class MyAdvice {
  
  public void doBefore() {
    System.out.printf("%s.doBefore()\n", this.getClass().getName());
  }
  
  public void doAfter() {
    System.out.printf("%s.doAfter()\n", this.getClass().getName());
  }
  
  public void doAfterReturning() {
    System.out.printf("%s.doAfterReturning()\n", this.getClass().getName());
  }
  
  public void doAfterThrowing() {
    System.out.printf("%s.doAfterThrowing()\n", this.getClass().getName());
  }
}




