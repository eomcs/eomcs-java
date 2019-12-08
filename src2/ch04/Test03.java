// 연산의 결과는 피연산자의 데이터 타입과 같다. 
package ch04;

public class Test03 {
  public static void main(String[] args) {
    // int와 int의 연산 결과는 int이다 .
    float r = 5 / 2; // r 변수에 값을 저장하기 전에 이미 결과는 2 이다.
    System.out.println(r);
    
    // 해결책!
    // => 두 개의 정수 값을 제대로 float으로 계산하고 싶다면 형변환 하라.
    // => 형변환? 다른 타입의 메모리를 임시로 만들어 값을 넣는 방법
    //    주의! 형변환이라고 해서 원래 변수나 값의 타입을 다른 타입으로 변경하는 것이 아니다.
    //    그래서 '형변환(type conversion)'을 '타입 캐스팅(type casting)'이라고도 부른다.
    // => 형변환 문법
    //    (새로 만들 메모리의 타입) 값
    // => 다음과 같이 개발자가 형변환을 명시하는 경우,
    //    "명시적 형변환(explicit type conversion(casting)"이라 부른다.
    //
    float r2 = (float)5 / (float)2;
    // float 타입의 임시 메모리가 생성된 후 정수 5가 저장된다.
    // float 타입의 임시 메모리가 생성된 후 정수 2가 저장된다.
    // 임시 생성된 두 개의 float 메모리 값을 계산한다.
    // 당연히 그 결과는 float 타입이다.
    System.out.println(r2);
    
    // 타입이 다른 경우 연산을 수행할 수 없다.
    // => 반드시 계산을 수행하는 피연산자의 타입이 같아야 한다.
    //    다르다면 내부적으로 두 피연산자의 값을 같은 타입으로 만든 후에 계산을 수행한다.
    //    즉 개발자가 지시하지 않아도 내부적으로 같은 타입으로 만드는 것을
    //    '암시적 형변환(implicit type conversion(casting)' 이라고 한다.
    // 
    float r3 = 5 / (float)2;
    System.out.println(r3);
    
    byte b1 = 20;
    byte b2 = 30;
    byte bb = 20 + 30;
    //byte b3 = b1 + b2; // 암시적 형변환에 의해 b1, b2의 값은 int 타입의 임시 메모리에 저장.
                       // 그런 후 계산된다. int와 int의 계산 결과는 당연히 int 이다.
                       // 그래서 컴파일 오류인 것이다.
    int x1 = b1 + b2; // OK!
    
    short s1 = 20;
    short s2 = 30;
    short s3 = -32768;
    short s4 = 32767;
    //short s3 = s1 + s2; // 위와 같다. 계산하기 전에 int 임시 메모리에 값이 저장된다.
    int x2 = s1 + s2; // OK!
    
    char cc = 'A'; //'문자' 의 의미? 해당 문자의 유니코드 값을 리턴하라!
    char c1 = 20;
    char c2 = 30;
    char c3 = 0;
    char c4 = 65535;
    //char c3 = c1 + c2; // 컴파일 오류!
    int x3 = c1 + c2; // OK!
    
    int i1 = 100;
    float f1 = 200.5f;
    //int i2 = i1 + f1; // 컴파일 오류!
    float f2 = i1 + f1;
    
    // 주의! 
    float f3 = 9876.543f;
    float f4 = 12.34567f;
    double d1 = f3 + f4; // float과 float의 계산 결과는 float이다. 
                         // d1에 저장되기 전에 자릿수(7자리)를 초과한 값은 오차값으로 저장된다.
    // 그래서 d1을 출력하면 오차가 있는 값이 출력되는 것이다.
    System.out.println(d1);
    
    // 해결책! 
    // => 계산하기 전에 더 큰 타입으로 형변환하라.
    double d2 = (double)f3 + (double)f4;
    // => 그런데 출력 결과를 보면 예산 결과와 다르게 나온다.
    //    이유? float을 double로 형변환할 때 오차가 이미 생긴다.
    //    부동소수점을 다룰 때 생기는 오차이다. 개발자가 제어할 수 없다.
    System.out.println(d2);
    
    // 해결책?
    // => 부동소수점의 경우 계산 결과가 float의 자릿수를 넘어갈 것 같으면 
    //    아예 처음부터 double에 저장해 놓고 계산하라.
    double d7 = 9876.543;
    double d8 = 12.34567;
    double d9 = d7 + d8;
    System.out.println(d9);
  }
}

/*
# 암시적 형변환의 규칙
- 서로 다른 타입과 연산을 수행한다면 다음 규칙에 따라 오른쪽 타입으로 자동 형변환을 수행한다.
- byte,short(-32768~32767),char(0~65535) => int => long => float => double 

예1) byte + short + char - float + long + int + double
  =>(int) + (int) : int 임시 메모리가 생성되고 byte 값이 저장된다.
    주의! byte, short, char를 연산할 때는 무조건 int로 형변환 한 다음에 수행한다.
    자바의 최소 연산 단위는 int이다.
  =>    (int)    +  char
  =>    (int)    + (int) 
  =>          (int)     - float
  =>          (float)   - float
  =>                 (float)  + long
  =>                 (float)  + (float)
  =>                        (float)   + int
  =>                        (float)   + (float)
  =>                                (float)  + double
  =>                                (double) + double
  =>                                       double
 */













