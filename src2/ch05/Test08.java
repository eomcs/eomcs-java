// 흐름제어문 - 반복문 : while, do ~ while, for(;;), for(:)
package ch05;

public class Test08 {
  
  public static void main(String[] args) {
    int a = 1;
    
    while (a <= 10) System.out.print(a++ + " ");
    
    System.out.println();
    
    a = 1;
    while (a <= 10) 
      System.out.print(a++ + " ");
    
    System.out.println();
    
    a = 1;
    while (a <= 10) { 
      System.out.print(a + " ");
      a++;
    }
    
  }
}

/*
# while 문법

  while (조건) 문장1;
  
  while (조건) {
    문장1;
    문장2;
  }
  
*/













