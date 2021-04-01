// Mybatis 설정하기
// 1) Mybatis 라이브러리 파일을 프로젝트에 등록하기
//    - mvnrepository.com에서 mybatis 검색하여 라이브러리 정보 알아낸다.
//    - build.gradle 파일에 의존 라이브러리 정보를 추가한다.
//    - 'gradle eclipse' 실행하여 라이브러리를 다운로드 받고, 이클립스 설정 파일에 등록한다.
//    - 이클립스 프로젝트를 리프래시하여 변경된 설정 파일의 정보를 반영한다.
// 2) Mybatis 설정 파일 준비
//    - mybatis.org 사이트에서 문서 페이지를 참조한다.
//    - Mybatis 설정 파일(예: mybatis-config.xml)을 생성한다.
//    - 문서 페이지를 참조하여 설정 파일의 내용을 변경한다.
// 3) SQL 매퍼 파일 준비
//    - mybatis.org 사이트에서 문서 페이지를 참조한다.
//    - SQL이 들어 있는 파일(예: BoardMapper.xml)을 생성한다.
//    - 문서 페이지를 참조하여 매퍼 파일의 내용을 변경한다.
// 4) select 결과를 저장할 클래스 정의
//    - ch26.a.Board 클래스 생성
//    - 필드는 컬럼의 이름과 같게 한다.
// 5) select SQL을 실행
//    - mybatis 문서 페이지를 참조한다.
//    - 관련 객체를 생성하고 사용하여 SQL을 실행한다.
//
package ch26.a;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test01 {

  public static void main(String[] args) throws Exception {
    
    // 1) mybatis 설정 파일을 읽을 때 사용할 도구를 준비한다.
    // => 파일 입력 스트림 객체 준비
    // => FileInputStream 객체를 직접 생성해도 되지만,
    //    직접 생성하려면 설정 파일의 전체 경로를 정확하게 지정해야 한다.
    //    예) c:/Users/user/git/bitcamp-2018-12/java-basic/src/main/java/ch26/a/mybatis-config.xml
    //    만약 경로가 바뀌면 소스의 경로를 다시 변경해야 하는 문제가 있다.
    // => mybatis 설정 파일이 자바 클래스 파일(.class)처럼 CLASSPATH에 있다면 
    //    Resources.getResourceAsStream()을 호출하여 입력 스트림을 준비하는 것이 더 편하다.
    // 
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/a/mybatis-config.xml");
    
    // 만약 Resources.getResourceAsStream()을 사용하지 않는다면 다음과 같이 
    // 직접 FileInputStream을 생성해야 한다.
    //InputStream inputStream2 = new FileInputStream(
    //    "/Users/eomjinyoung/git/bitcamp-java-2018-12/java-basic/src/main/java/ch26/a/mybatis-config.xml");
    
    // 2) mybatis 설정 파일에 따라 동작할 SQL 실행 도구를 준비한다.
    // => SqlSessionFactoryBuilder 
    //    - SqlSessionFactory 객체를 만들어 주는 일.
    //    - Builder 디자인 패턴이 적용되어 있다.
    //      (여러 객체를 조립하여 한 객체를 생성하는 경우, 직접 하는 대신에 
    //       그 일을 해주는 객체 '빌더'를 통해 생성하는 것이 유지보수에 좋다.)
    //
    // => SqlSessionFactory 
    //    - SqlSession 객체를 만들어 주는 일.
    //    - Factory Method 디자인 패턴이 적용되어 있다.
    //      (객체 생성 과정이 복잡할 경우 메서드를 통해 객체를 생성하는 것이 유지보수에 좋다.)
    // 
    // => SqlSession : SQL 매퍼 파일에 보관된 SQL을 찾아 실행해주는 일.
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);

    // 3) 팩토리 메서드를 통해 객체를 생성한다.
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    // 4) SQL 매퍼 파일에 보관된 SQL 문을 찾아 실행한다.
    // => 파라미터 값은 SQL 문의 id 이다.
    // => SQL 매퍼 파일의 namespace 값과 SQL ID 값을 결합해서 지정한다.
    // => selectList()의 리턴 값은 SQL 매퍼 파일의 resultType에 지정된 객체를 담고 있는 
    //    List 객체이다.
    List<Board> list = sqlSession.selectList("board.select1");
    
    // 5) 출력!
    for (Board b : list) {
      System.out.printf("%d, %s, %s, %s, %d\n", 
          b.getBoard_id(),
          b.getTitle(),
          b.getContents(),
          b.getCreated_date(),
          b.getView_count());
    }
    
    sqlSession.close();
  }

}






