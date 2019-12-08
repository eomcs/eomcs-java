package com.eomcs.basic.ex06x.assignment;

import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) {
        Scanner keyScan = new Scanner(System.in);
        System.out.print("가로 길이? ");
        int len = keyScan.nextInt();

        int count = 1;
        while (count <= len) {
            int starCnt = 1;
            while (starCnt <= count) {
                System.out.print("*");
                starCnt++;
            }
            System.out.println();
            count++;
        }

        count = len - 1;
        while (count >= 1) {
            int starCnt = 1;
            while (starCnt <= count) {
                System.out.print("*");
                starCnt++;
            }
            System.out.println();
            count--;
        }
    }
}