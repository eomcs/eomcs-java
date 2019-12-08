// 메서드 - 가변 파라미터의 단점
package ch06;

public class Test08 {
  public static void main(String[] args) {
    compute("홍길동", 100, 100, 90);
    compute("임꺽정");
    compute("유관순", 100, 100, 100, 100);
    
    compute3(new int[] {100, 100, 90}, "홍길동");
    compute3(new int[] {}, "임꺽정");
    compute3(new int[] {100, 100, 100, 100}, "유관순");
    
    compute4(new int[] {100, 90, 80}, new String[] {"홍길동", "임꺽정", "유관순"});
  }
  
  // 1) 가변 파라미터는 무조건 마지막 순서에 와야 한다. 
  //static int plus1(int... value, String name) { // 컴파일 오류!
  static void compute(String name, int... value) {  
    int sum = 0; 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    System.out.printf("%s 님의 총점은 %d입니다.\n", name, sum);
  }
  
  // 2) 가변 파라미터는 1개만 가능하다. 
  /*
  static int compute2(int...value, String... name) {// 컴파일 오류!
    int sum = 0; 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    return sum;
  }
  */
  
  // 배열은 순서에 상관없다. 어느 순서에도 올 수 있다.
  static void compute3(int[] value, String name) {  
    int sum = 0; 
    for (int i = 0; i < value.length; i++) {
      sum += value[i];
    }
    System.out.printf("%s 님의 총점은 %d입니다.\n", name, sum);
  }

  // 여러 개의 배열을 파라미터의 값으로 넘길 수 있다.
  static void compute4(int[] value, String[] name) {  
    for (int i = 0; i < value.length; i++) {
      System.out.printf("%s 님의 점수는 %d입니다.\n", name[i], value[i]);
    }
  }  
  
}















