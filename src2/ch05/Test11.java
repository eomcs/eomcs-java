// 흐름제어문 - do ~ while 반복문
package ch05;

public class Test11 {
  
  public static void main(String[] args) {
    int i = 1;
    do 
      System.out.print(i + " ");
    while (i++ < 10);
    System.out.println();
    
    i = 0;
    do {
      System.out.print(++i);
      System.out.print(",");
    } while (i < 10);
    System.out.println();
  }
}

/*
# do ~ while 
- 최소 한 번은 반복한다.
- 한 번 이상 반복하면 do ~ while 
- 0 번 이상 반복하면 while

  do
    문장1;
  while (조건);
  
  do {
    문장1;
    문장2;
    문장3;
  } while (조건);
 */













