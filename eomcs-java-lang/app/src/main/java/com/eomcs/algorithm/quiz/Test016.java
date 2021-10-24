package com.eomcs.algorithm.quiz;
//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Determine whether the given string is a concatenation of another string 
to itself.

Example

for 'tandemtandem' output should be true

for 'qqq' or '2w2ww' output should be false

[input] string inputString

[output] boolean

true if inputString represents a string concatenated to itself, 
false otherwise

 */
public class Test016 {
  static boolean isTandemRepeat(String inputString) {

    int len = inputString.length();
    if (len % 2 == 1) {
      return false;
    }
    for (int i = 0; i < len; i++) {
      if (inputString.charAt(i) != inputString.charAt(i + len / 2)) {
        return false;
      }
    }

    return true;
  }
  
  public static void main(String[] args) {
    System.out.println(isTandemRepeat("abcdeabcde"));

  }

}
