package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
A fraction written as a/b is said to be a proper fraction 
if the absolute value of the fraction is strictly less than one. 
Otherwise it is called an improper fraction.

Determine whether a given fraction is proper.

Example

properOrImproper([7, 2]) = "Improper"

[input] array.integer A

array of two integers representing a fraction A[0] / A[1]
[output] string

"Proper" if A contains a proper fraction, "Improper" otherwise
 */
public class Test015 {
  static String properOrImproper(int[] A) {

    if (A[0] / A[1] < 1) {
      return "Proper";
    }
    return "Improper";
  }


  public static void main(String[] args) {
    System.out.println(properOrImproper(new int[]{7,2}));
    System.out.println(properOrImproper(new int[]{-3,3}));

  }
}
