// 변수 선언과 메모리 크기
package ch03;

public class Test01 {
  public static void main(String[] args) {

    

    
    // 논리 값을 담을 메모리 준비
    boolean bool1;
    boolean bool2;
    boolean bool3;
    
    bool1 = true;
    bool2 = false;
    //bool3 = 1; // 컴파일 오류! true, false 외의 다른 값은 저장할 수 없다.
    
    // 문자의 UTF-16 코드 값을 담을 메모리 준비
    char c1;
    char c2;
    char c3;
    
    c1 = 44032; // 4바이트 정수 리터럴이더라도 0 ~ 65535까지의 값이라면 
                // char 메모리에 저장하는 것을 허락한다.
    c2 = 0xac00;
    c3 = '가'; // '' 의 리턴 값은 2바이트(0 ~ 65535) 유니코드 정수 값이다.
    
    System.out.println(c1); // 변수 자체가 char 이기 때문에 (char)를 붙이지 않아도 된다.
    System.out.println(c2);
    System.out.println(c3);
    
  }
}

/*
# 변수
- 값을 저장하는 메모리

# 변수 선언
- 값을 저장하는 메모리를 준비하는 명령이다.
- 값을 저장할 메모리의 위치와 크기를 결정하고 그 메모리에 이름을 부여한다.
- 변수를 선언한 후 바로 그 이름을 사용하여 메모리에 접근하고 값을 넣고 꺼낸다.
- 보통 "변수를 생성한다"라고 표현하기도 한다.
- 문법
  메모리의종류  메모리이름;
  int a;
  
# 메모리 종류
1) primitive data type(자바 원시 데이터 타입, 자바 기본 데이터 타입)
- 정수
  - byte(1바이트) : -128 ~ 127 
  - short(2바이트) : -32768 ~ 32767
  - int(4바이트) : 약 -21억 ~ 21억
  - long(8바이트) : 약 -900경 ~ 900경
- 부동소수점
  - float(4바이트) : 유효자릿수 7자리
  - double(8바이트) : 유효자릿수 15자리
- 논리
  - boolean(JVM에서 int 크기)
- 문자
  - char(2바이트) : 0 ~ 65535. UTF-16 코드 값 저장

2) 레퍼런스(reference)
- 객체(데이터들의 덩어리)의 주소를 저장하는 변수
- 자바 기본 타입을 제외한 나머지 모두!

# 변수의 이름
- 보통 소문자로 시작한다.
- 대소문자를 구분한다.
- 여러 단어로 구성된 경우 두 번째 단어의 시작 알파벳은 대문자로 한다.
  - 예) firstName, createdDate, userId
- 상수인 경우 보통 모두 대문자로 이름을 짓는다. 단어와 단어 사이는 _를 사용한다.
  - 예) USER_TYPE, USER_MANAGER
 
 */















