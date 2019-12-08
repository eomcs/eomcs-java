// JVM 아규먼트
package ch06;

public class Test16 {
  public static void main(String[] args) {
    // JVM 아규먼트?
    // - JVM에게 전달하는 값
    // - 형식
    //   $java -cp ./bin/main -D이름=값 -D이름=값 -D이름=값 ch06.Test16
    //
    
    // JVM 아규먼트의 값 꺼내기
    // => System.getProperty("이름");
    // 
    String value1 = System.getProperty("a");
    String value2 = System.getProperty("b");
    String value3 = System.getProperty("c");
    
    System.out.println(value1);
    System.out.println(value2);
    System.out.println(value3);
  }
  
}















