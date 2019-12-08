// 비트 이동 연산자 III : >>, >>>, <<
package ch04;

public class Test16 {
  public static void main(String[] args) {
    // << 연산자
    // => 왼쪽으로 비트를 이동시킨다. 
    // => 왼쪽 경계를 넘어 가는 값은 버린다. 
    // => 오른쪽 빈자리는 무조건 0으로 채운다.
    // => n * 2**x 한 것과 같은 결과가 나온다.
    int a = 0xc9; // = 201
    System.out.println(Integer.toHexString(a << 1));
    System.out.println(a << 1);
    //   00000000_00000000_00000000_11001001
    // 0|00000000_00000000_00000001_10010010 = 0x00_00_01_92
    
    System.out.println(Integer.toHexString(a << 2)); 
    System.out.println(a << 2);
    //   00000000_00000000_00000000_11001001
    //00|00000000_00000000_00000011_00100100 = 0x00_00_03_24
    
    // 0111 1111 1111 1111 1111 1111 1111 1010 =  21_4748_3642
    // 1000 0000 0000 0000 0000 0000 0000 0110 = -21_4748_3642
    
    // 음수를 왼쪽으로 이동할 때는 부호비트에 상관없이 무조건 이동 
    a = -0x7f_ff_ff_fa;
    System.out.println(a);
    System.out.println(a << 1); // 1|0000 0000 0000 0000 0000 0000 0000 1100 = 12
    System.out.println(a << 2);  
    System.out.println(a << 3);   
    System.out.println(a << 4);
    
    // 결론!
    // 왼쪽으로 x 만큼 비트를 이동시키는 것은 주어진 양수 대해 2**x 로 곱한 것과 같다.
    // 주의! 
    // 음수의 경우는 양수로 바뀔 수 있다. 
  }
}

















