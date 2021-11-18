// Log4j 2 사용법
package com.eomcs.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Log4j
// - 프로그램을 실행하는 중에 특정 변수의 값이나 실행 상태, 오류 내용을 
//   콘솔이나 파일에 기록하고 싶을 때 사용하는 라이브러리이다.
// - 단계 별로 출력을 제어할 수 있다.
// - 프로그램 실행 과정을 추적하거나 디버깅 할 때 유용하다.
// 
// 사용법
// 1) log4j 라이브러리 파일을 프로젝트에 포함한다.
//    - search.maven.org 사이트에서 'log4j-core' 로 검색한다.
//      예) org.apache.logging.log4j:log4j-core:2.14.1
//    - 라이브러리 정보를 build.gradle 에 포함시킨다.
//    - '$ gradle eclipse'를 실행하여 라이브러리를 가져오고, 이클립스 설정 파일을 갱신한다. 
// 2) log4j 설정 파일을 준비 
//    - CLASSPATH 루트 경로에 log4j2.xml 파일 작성
//    - 문서를 참조하여 설정한다.
// 
public class Exam01 {

  // 기록을 남길 때 사용할 도구 준비
  // - 클래스 당 한 개의 도구만 있어도 된다.
  private static final Logger logger = LogManager.getLogger(Exam01.class); 

  public static void main(String[] args) {

    // 로깅 도구 없이 기록을 남기는 방법
    System.out.println("고전적인 방법으로 기록을 남기기!");

    // 로깅 도구를 사용하여 기록을 남기는 방법
    logger.fatal("내용 ==> FATAL");
    logger.error("내용 ==> ERROR");
    logger.warn("내용 ==> WARN");
    logger.info("내용 ==> INFO");
    logger.debug("내용 ==> DEBUG");
    logger.trace("내용 ==> TRACE");
  }
}








