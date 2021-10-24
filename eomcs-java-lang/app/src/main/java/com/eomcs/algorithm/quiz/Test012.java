package com.eomcs.algorithm.quiz;

//출처: codefights.com
//한 줄에 버그가 있다. 고쳐라!
/*
Given a point and a circle, determine if the point lies strictly 
inside the circle.

Example

isTheInsideTheCircle(0, 0, 0, 0, 1) = true
isTheInsideTheCircle(-1, -1, 0, 0, 1) = false
[input] integer xa

x coordinate of the given point
[input] integer ya

y coordinate of the given point
[input] integer xc

x coordinate of the center of the circle
[input] integer yc

y coordinate of the center of the circle
[input] integer rc

the radius of the circle
[output] boolean

true, if the point (xa, ya) lies inside the circle (xc, yc, rc), false otherwise
 */
public class Test012 {
  static boolean isInsideTheCircle(int xa, int ya, int xc, int yc, int rc) {
    int dist = (xa - xc) * (xa - xc) + (ya - yc) * (ya - yc);
    rc *= rc;
    if (dist <= rc) {
      return true;
    }
    return false;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
