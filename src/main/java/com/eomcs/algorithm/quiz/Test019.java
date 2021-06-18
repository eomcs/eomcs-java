package com.eomcs.algorithm.quiz;
//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
You are given an array of integers representing coordinates of obstacles 
situated on a straight line.

Assume that you are jumping from the point with coordinate 0 to the right. 
You are allowed only to make jumps of the same length represented 
by some integer.

Find the minimal length of the jump enough to avoid all the obstacles.

Example

for [5, 3, 6, 7, 9] output should be 4

[input] array.integer inputArray

non-empty array of positive integers
[output] integer

the desired length
 */
public class Test019 {
  static int avoidObstacles(int[] inputArray) {

    for (int i = 1; ; i++) {
      for (int j = 0; j < inputArray.length; j++) {
        if (inputArray[j] % i == 0) {
          break;
        }
        if (j == inputArray.length - 1) {
          return i;
        }
      }
    }  
  }
  public static void main(String[] args) {
    System.out.println(avoidObstacles(new int[]{5,3,6,7,9}));

  }

}
