//# 클래스 블록과 컴파일
//
//- 자바 컴파일러는 클래스 블록 단위로 컴파일을 수행한다.
//- 클래스 블록 단위로 클래스 파일(.class 파일)을 생성한다.
//
class A {}
class B {}
class C {}
 
//## 실습
//1) 컴파일하기
//     eomcs-java-basic$ javac -d bin/main -encoding UTF-8 src/main/java/com/eomcs/basic/ch01/solution/Exam03.java
//2) 생성된 클래스 파일 확인하기
//- bin/main/com/eomcs/basic/ch01/solution 디렉토리에 A.class, B.class, C.class 파일이 생성된다
//






