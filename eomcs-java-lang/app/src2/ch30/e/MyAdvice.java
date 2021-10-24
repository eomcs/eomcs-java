package ch30.e;

// 지정된 객체의 메서드를 호출할 때,
// 메서드 호출 전이나 후에 어떤 작업을 수행하는 일을 한다.
public class MyAdvice {
  
  // 메서드를 호출하기 전에 미리 파라미터 값을 받을 수 있다.
  // => advice에 파라미터 값을 받을 변수를 선언하라.
  // => XML 설정에서 파라미터 값을 받겠다고 선언해야 한다.
  public void doBefore(String p1, int p2, int p3) {
    System.out.printf("%s.doBefore()\n", this.getClass().getName());
    System.out.printf("  => %s %d %d\n", p1, p2, p3);
  }
  
  public void doAfter() {
    System.out.printf("%s.doAfter()\n", this.getClass().getName());
  }
  
  // 메서드를 호출한 후 그 리턴 값을 받을 수 있다.
  // => advice에 리턴 값을 받을 파라미터를 선언하라.
  // => XML 설정에서 어떤 파라미터가 리턴 값을 받을 것인지 선언해야 한다.
  public void doAfterReturning(Object rv) {
    System.out.printf("%s.doAfterReturning()\n", this.getClass().getName());
    System.out.printf("  => %d\n", rv);
  }
  
  // 메서드가 던지 예외를 받을 수 있다.
  // => advice에 예외를 받을 파라미터를 선언하라.
  // => XML 설정에서 어떤 파라미터가 예외를 받을 것인지 선언해야 한다.
  public void doAfterThrowing(Exception err) {
    System.out.printf("%s.doAfterThrowing()\n", this.getClass().getName());
    System.out.printf("  => %s\n", err.getMessage());
  }
}




