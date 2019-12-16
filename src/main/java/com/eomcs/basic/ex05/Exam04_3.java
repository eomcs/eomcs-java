// 비트 이동 연산자 : >>, >>>, << 
package com.eomcs.basic.ex05;

public class Exam04_3 {
    public static void main(String[] args) {
        int i = 0b01101001; // 105
        System.out.println(i); 
        System.out.println(i >>> 1); // 00110100|1 => 52
        System.out.println(i >>> 2); // 00011010|0 => 26
        System.out.println(i >>> 3); // 00001101|0 => 13
        System.out.println(i >>> 4); // 00000110|1 => 6
        // >>> 연산자를 사용하여 오른쪽으로 이동한다면, 
        // 왼쪽 쪽 빈자리는 무조건 0으로 채운다.
        // 그래서 양수일 때는 >> 연산자와 같은 결과가 나오지만,
        // 다음과 같이 음수 일때는 양수로 바뀌는 결과가 나온다.

        // 음수를 이동
        i = 0b11111111_11111111_11111111_10101001; // -87
        System.out.println(i); 
        System.out.println(i >>> 1); 
        // 0_11111111_11111111_11111111_1010100|1  
        // => 왼쪽 빈자리는 0으로 채운다.
        //    즉 양수로 바뀐다.
        //    양수로 바뀌는 순간 엄청나가 큰 수가 된다.

        System.out.println(i >>> 2); 
        // 00_11111111_11111111_11111111_101010|01

        System.out.println(i >>> 3); 
        // 000_11111111_11111111_11111111_10101|001
        
        System.out.println(i >>> 4);
        // 0000_11111111_11111111_11111111_1010|1001 
    }
}
