package com.eomcs.algorithm.quiz;

//copyright by codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Reverse an array

Example

myReverse([1, 3, 2]) = [2, 3, 1]

[input] array.integer input

[output] array.integer
 */
public class Test026 {
  static int[] myReverse(int[] input) {

    for (int i = 0; i * 2 < input.length; i++) {
      int tmp = input[i];
      input[i] = input[input.length - i];
      input[input.length - i - 1] = tmp;
    }
    return input;
  }


  public static void main(String[] args) {
    int[] reverseList = myReverse(new int[]{9,8,7,6,5,4,3,2,1});
    for (int i : reverseList) {
      System.out.print(i + ",");
    }
  }

}
