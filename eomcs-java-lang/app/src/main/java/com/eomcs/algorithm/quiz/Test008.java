package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라! 
/*
Given a list of fractions find the largest one.

Example

for numerators=[5, 2, 5], denominators=[6, 3, 4] output 
should be 2 since 5/4 is the largest fraction

[input] array.integer numerators

array of integers representing numerators of the fractions
[input] array.integer denominators

array of integers of the same length as numerators representing denominators 
where the i-th fraction equals to numerators[i]/denominators[i]
[output] integer

index of the largest fraction assuming that none of the fractions are equal
 */

public class Test008 {
  static int maxFraction(int[] numerators, int[] denominators) {

    int maxFractionIndex = 0;
    for (int i = 1; i < numerators.length; i++) {
      if (numerators[i] * denominators[maxFractionIndex] >
        denominators[maxFractionIndex] * numerators[i]) {
        maxFractionIndex = i;
      }
    }
    return maxFractionIndex;
  }
  
  public static void main(String[] args) {
    System.out.println(maxFraction(
        new int[]{5, 2, 5}, new int[]{6, 3, 4}));

  }

}
