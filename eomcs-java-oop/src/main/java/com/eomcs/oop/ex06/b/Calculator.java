package com.eomcs.oop.ex06.b;

public class Calculator {
    static int plus(int a, int b) {
        return a + b;
    }
    
    static float plus(float a, float b) {
        return a + b;
    }
    
    static int plus(String a, String b) {
        int v1 = Integer.parseInt(a);
        int v2 = Integer.parseInt(b);
        return v1 + v2;
    }
    
}






