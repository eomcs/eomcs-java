-- 강의
DROP TABLE IF EXISTS lms_lecture RESTRICT;

-- 강사
DROP TABLE IF EXISTS lms_tchr RESTRICT;

-- 수강생
DROP TABLE IF EXISTS lms_stud RESTRICT;

-- 매니저
DROP TABLE IF EXISTS lms_mgr RESTRICT;

-- 강의실
DROP TABLE IF EXISTS lms_classroom RESTRICT;

-- 회원
DROP TABLE IF EXISTS lms_member RESTRICT;

-- 교육센터
DROP TABLE IF EXISTS lms_center RESTRICT;

-- 부서
DROP TABLE IF EXISTS lms_dept RESTRICT;

-- 직급
DROP TABLE IF EXISTS lms_position RESTRICT;

-- 학력
DROP TABLE IF EXISTS lms_grade RESTRICT;

-- 고용형태
DROP TABLE IF EXISTS lms_work_type RESTRICT;

-- 강의실사진
DROP TABLE IF EXISTS lms_classroom_photo RESTRICT;

-- 주소
DROP TABLE IF EXISTS lms_addr RESTRICT;

-- 수강신청
DROP TABLE IF EXISTS lms_lect_appl RESTRICT;

-- 강사배정
DROP TABLE IF EXISTS lms_lect_tchr RESTRICT;

-- 강의
CREATE TABLE lms_lecture (
  lno      INTEGER      NOT NULL COMMENT '강의번호', -- 강의번호
  title    VARCHAR(255) NOT NULL COMMENT '강의명', -- 강의명
  content  LONGTEXT     NOT NULL COMMENT '강의내용', -- 강의내용
  sdt      DATE         NOT NULL COMMENT '시작일', -- 시작일
  edt      DATE         NOT NULL COMMENT '종료일', -- 종료일
  qty      INTEGER      NOT NULL DEFAULT 0 COMMENT '수용인원', -- 수용인원
  price    INTEGER      NULL     DEFAULT 0 COMMENT '강의료', -- 강의료
  total_hr INTEGER      NOT NULL DEFAULT 0 COMMENT '총수업시간', -- 총수업시간
  day_hr   INTEGER      NULL     DEFAULT 0 COMMENT '일수업시간', -- 일수업시간
  mgrno    INTEGER      NULL     COMMENT '매니저번호', -- 매니저번호
  crno     INTEGER      NULL     COMMENT '강의실번호' -- 강의실번호
)
COMMENT '강의';

-- 강의
ALTER TABLE lms_lecture
  ADD CONSTRAINT PK_lms_lecture -- 강의 기본키
    PRIMARY KEY (
      lno -- 강의번호
    );

-- 강의 인덱스
CREATE INDEX IX_lms_lecture
  ON lms_lecture( -- 강의
    title ASC -- 강의명
  );

ALTER TABLE lms_lecture
  MODIFY COLUMN lno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의번호';

-- 강사
CREATE TABLE lms_tchr (
  tno    INTEGER NOT NULL COMMENT '강사번호', -- 강사번호
  hr_pay INTEGER NULL     DEFAULT 0 COMMENT '시급', -- 시급
  wtno   INTEGER NOT NULL COMMENT '고용형태번호' -- 고용형태번호
)
COMMENT '강사';

-- 강사
ALTER TABLE lms_tchr
  ADD CONSTRAINT PK_lms_tchr -- 강사 기본키
    PRIMARY KEY (
      tno -- 강사번호
    );

-- 수강생
CREATE TABLE lms_stud (
  sno     INTEGER     NOT NULL COMMENT '수강생번호', -- 수강생번호
  gno     INTEGER     NOT NULL COMMENT '학력번호', -- 학력번호
  sch_nm  VARCHAR(20) NULL     COMMENT '최종학교', -- 최종학교
  major   VARCHAR(20) NULL     COMMENT '전공', -- 전공
  working BOOLEAN     NOT NULL DEFAULT false COMMENT '재직여부' -- 재직여부
)
COMMENT '수강생';

-- 수강생
ALTER TABLE lms_stud
  ADD CONSTRAINT PK_lms_stud -- 수강생 기본키
    PRIMARY KEY (
      sno -- 수강생번호
    );

-- 매니저
CREATE TABLE lms_mgr (
  mgrno   INTEGER     NOT NULL COMMENT '매니저번호', -- 매니저번호
  dno     INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
  pono    INTEGER     NOT NULL COMMENT '직급번호', -- 직급번호
  ext_tel VARCHAR(30) NULL     COMMENT '사내전화번호', -- 사내전화번호
  fax     VARCHAR(30) NULL     COMMENT '팩스' -- 팩스
)
COMMENT '매니저';

-- 매니저
ALTER TABLE lms_mgr
  ADD CONSTRAINT PK_lms_mgr -- 매니저 기본키
    PRIMARY KEY (
      mgrno -- 매니저번호
    );

-- 강의실
CREATE TABLE lms_classroom (
  crno  INTEGER     NOT NULL COMMENT '강의실번호', -- 강의실번호
  cntno INTEGER     NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  name  VARCHAR(20) NOT NULL COMMENT '강의실명', -- 강의실명
  qty   INTEGER     NOT NULL DEFAULT 0 COMMENT '최대수용인원' -- 최대수용인원
)
COMMENT '강의실';

-- 강의실
ALTER TABLE lms_classroom
  ADD CONSTRAINT PK_lms_classroom -- 강의실 기본키
    PRIMARY KEY (
      crno -- 강의실번호
    );

-- 강의실 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_classroom
  ON lms_classroom ( -- 강의실
    cntno ASC, -- 교육센터번호
    name ASC   -- 강의실명
  );

ALTER TABLE lms_classroom
  MODIFY COLUMN crno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실번호';

-- 회원
CREATE TABLE lms_member (
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name     VARCHAR(20)  NOT NULL COMMENT '이름', -- 이름
  email    VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  tel      VARCHAR(30)  NOT NULL COMMENT '휴대폰', -- 휴대폰
  ano      INTEGER      NULL     COMMENT '주소번호', -- 주소번호
  bas_addr VARCHAR(255) NULL     COMMENT '상세주소' -- 상세주소
)
COMMENT '회원';

-- 회원
ALTER TABLE lms_member
  ADD CONSTRAINT PK_lms_member -- 회원 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_member
  ON lms_member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_lms_member2
  ON lms_member ( -- 회원
    tel ASC -- 휴대폰
  );

-- 회원 인덱스
CREATE INDEX IX_lms_member
  ON lms_member( -- 회원
    name ASC -- 이름
  );

ALTER TABLE lms_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 교육센터
CREATE TABLE lms_center (
  cntno INTEGER     NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  name  VARCHAR(20) NOT NULL COMMENT '센터명' -- 센터명
)
COMMENT '교육센터';

-- 교육센터
ALTER TABLE lms_center
  ADD CONSTRAINT PK_lms_center -- 교육센터 기본키
    PRIMARY KEY (
      cntno -- 교육센터번호
    );

-- 교육센터 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_center
  ON lms_center ( -- 교육센터
    name ASC -- 센터명
  );

ALTER TABLE lms_center
  MODIFY COLUMN cntno INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육센터번호';

-- 부서
CREATE TABLE lms_dept (
  dno  INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
  name VARCHAR(20) NOT NULL COMMENT '부서명' -- 부서명
)
COMMENT '부서';

-- 부서
ALTER TABLE lms_dept
  ADD CONSTRAINT PK_lms_dept -- 부서 기본키
    PRIMARY KEY (
      dno -- 부서번호
    );

-- 부서 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_dept
  ON lms_dept ( -- 부서
    name ASC -- 부서명
  );

ALTER TABLE lms_dept
  MODIFY COLUMN dno INTEGER NOT NULL AUTO_INCREMENT COMMENT '부서번호';

-- 직급
CREATE TABLE lms_position (
  pono INTEGER     NOT NULL COMMENT '직급번호', -- 직급번호
  name VARCHAR(20) NOT NULL COMMENT '직급명' -- 직급명
)
COMMENT '직급';

-- 직급
ALTER TABLE lms_position
  ADD CONSTRAINT PK_lms_position -- 직급 기본키
    PRIMARY KEY (
      pono -- 직급번호
    );

-- 직급 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_position
  ON lms_position ( -- 직급
    name ASC -- 직급명
  );

ALTER TABLE lms_position
  MODIFY COLUMN pono INTEGER NOT NULL AUTO_INCREMENT COMMENT '직급번호';

-- 학력
CREATE TABLE lms_grade (
  gno   INTEGER     NOT NULL COMMENT '학력번호', -- 학력번호
  title VARCHAR(20) NOT NULL COMMENT '학력명' -- 학력명
)
COMMENT '학력';

-- 학력
ALTER TABLE lms_grade
  ADD CONSTRAINT PK_lms_grade -- 학력 기본키
    PRIMARY KEY (
      gno -- 학력번호
    );

-- 학력 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_grade
  ON lms_grade ( -- 학력
    title ASC -- 학력명
  );

ALTER TABLE lms_grade
  MODIFY COLUMN gno INTEGER NOT NULL AUTO_INCREMENT COMMENT '학력번호';

-- 고용형태
CREATE TABLE lms_work_type (
  wtno  INTEGER     NOT NULL COMMENT '고용형태번호', -- 고용형태번호
  title VARCHAR(20) NOT NULL COMMENT '고용형태명' -- 고용형태명
)
COMMENT '고용형태';

-- 고용형태
ALTER TABLE lms_work_type
  ADD CONSTRAINT PK_lms_work_type -- 고용형태 기본키
    PRIMARY KEY (
      wtno -- 고용형태번호
    );

-- 고용형태 유니크 인덱스
CREATE UNIQUE INDEX UIX_lms_work_type
  ON lms_work_type ( -- 고용형태
    title ASC -- 고용형태명
  );

ALTER TABLE lms_work_type
  MODIFY COLUMN wtno INTEGER NOT NULL AUTO_INCREMENT COMMENT '고용형태번호';

-- 강의실사진
CREATE TABLE lms_classroom_photo (
  crpno    INTEGER      NOT NULL COMMENT '강의실사진번호', -- 강의실사진번호
  crno     INTEGER      NOT NULL COMMENT '강의실번호', -- 강의실번호
  filepath VARCHAR(255) NOT NULL COMMENT '사진파일명' -- 사진파일명
)
COMMENT '강의실사진';

-- 강의실사진
ALTER TABLE lms_classroom_photo
  ADD CONSTRAINT PK_lms_classroom_photo -- 강의실사진 기본키
    PRIMARY KEY (
      crpno -- 강의실사진번호
    );

ALTER TABLE lms_classroom_photo
  MODIFY COLUMN crpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실사진번호';

-- 주소
CREATE TABLE lms_addr (
  ano      INTEGER      NOT NULL COMMENT '주소번호', -- 주소번호
  pstno    VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '주소';

-- 주소
ALTER TABLE lms_addr
  ADD CONSTRAINT PK_lms_addr -- 주소 기본키
    PRIMARY KEY (
      ano -- 주소번호
    );

ALTER TABLE lms_addr
  MODIFY COLUMN ano INTEGER NOT NULL AUTO_INCREMENT COMMENT '주소번호';

-- 수강신청
CREATE TABLE lms_lect_appl (
  lno INTEGER NOT NULL COMMENT '강의번호', -- 강의번호
  sno INTEGER NOT NULL COMMENT '수강생번호' -- 수강생번호
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE lms_lect_appl
  ADD CONSTRAINT PK_lms_lect_appl -- 수강신청 기본키
    PRIMARY KEY (
      lno, -- 강의번호
      sno  -- 수강생번호
    );

-- 강사배정
CREATE TABLE lms_lect_tchr (
  lno INTEGER NOT NULL COMMENT '강의번호', -- 강의번호
  tno INTEGER NOT NULL COMMENT '강사번호' -- 강사번호
)
COMMENT '강사배정';

-- 강사배정
ALTER TABLE lms_lect_tchr
  ADD CONSTRAINT PK_lms_lect_tchr -- 강사배정 기본키
    PRIMARY KEY (
      lno, -- 강의번호
      tno  -- 강사번호
    );

-- 강의
ALTER TABLE lms_lecture
  ADD CONSTRAINT FK_lms_mgr_TO_lms_lecture -- 매니저 -> 강의
    FOREIGN KEY (
      mgrno -- 매니저번호
    )
    REFERENCES lms_mgr ( -- 매니저
      mgrno -- 매니저번호
    );

-- 강의
ALTER TABLE lms_lecture
  ADD CONSTRAINT FK_lms_classroom_TO_lms_lecture -- 강의실 -> 강의
    FOREIGN KEY (
      crno -- 강의실번호
    )
    REFERENCES lms_classroom ( -- 강의실
      crno -- 강의실번호
    );

-- 강사
ALTER TABLE lms_tchr
  ADD CONSTRAINT FK_lms_member_TO_lms_tchr -- 회원 -> 강사
    FOREIGN KEY (
      tno -- 강사번호
    )
    REFERENCES lms_member ( -- 회원
      mno -- 회원번호
    );

-- 강사
ALTER TABLE lms_tchr
  ADD CONSTRAINT FK_lms_work_type_TO_lms_tchr -- 고용형태 -> 강사
    FOREIGN KEY (
      wtno -- 고용형태번호
    )
    REFERENCES lms_work_type ( -- 고용형태
      wtno -- 고용형태번호
    );

-- 수강생
ALTER TABLE lms_stud
  ADD CONSTRAINT FK_lms_member_TO_lms_stud -- 회원 -> 수강생
    FOREIGN KEY (
      sno -- 수강생번호
    )
    REFERENCES lms_member ( -- 회원
      mno -- 회원번호
    );

-- 수강생
ALTER TABLE lms_stud
  ADD CONSTRAINT FK_lms_grade_TO_lms_stud -- 학력 -> 수강생
    FOREIGN KEY (
      gno -- 학력번호
    )
    REFERENCES lms_grade ( -- 학력
      gno -- 학력번호
    );

-- 매니저
ALTER TABLE lms_mgr
  ADD CONSTRAINT FK_lms_member_TO_lms_mgr -- 회원 -> 매니저
    FOREIGN KEY (
      mgrno -- 매니저번호
    )
    REFERENCES lms_member ( -- 회원
      mno -- 회원번호
    );

-- 매니저
ALTER TABLE lms_mgr
  ADD CONSTRAINT FK_lms_dept_TO_lms_mgr -- 부서 -> 매니저
    FOREIGN KEY (
      dno -- 부서번호
    )
    REFERENCES lms_dept ( -- 부서
      dno -- 부서번호
    );

-- 매니저
ALTER TABLE lms_mgr
  ADD CONSTRAINT FK_lms_position_TO_lms_mgr -- 직급 -> 매니저
    FOREIGN KEY (
      pono -- 직급번호
    )
    REFERENCES lms_position ( -- 직급
      pono -- 직급번호
    );

-- 강의실
ALTER TABLE lms_classroom
  ADD CONSTRAINT FK_lms_center_TO_lms_classroom -- 교육센터 -> 강의실
    FOREIGN KEY (
      cntno -- 교육센터번호
    )
    REFERENCES lms_center ( -- 교육센터
      cntno -- 교육센터번호
    );

-- 회원
ALTER TABLE lms_member
  ADD CONSTRAINT FK_lms_addr_TO_lms_member -- 주소 -> 회원
    FOREIGN KEY (
      ano -- 주소번호
    )
    REFERENCES lms_addr ( -- 주소
      ano -- 주소번호
    );

-- 강의실사진
ALTER TABLE lms_classroom_photo
  ADD CONSTRAINT FK_lms_classroom_TO_lms_classroom_photo -- 강의실 -> 강의실사진
    FOREIGN KEY (
      crno -- 강의실번호
    )
    REFERENCES lms_classroom ( -- 강의실
      crno -- 강의실번호
    );

-- 수강신청
ALTER TABLE lms_lect_appl
  ADD CONSTRAINT FK_lms_lecture_TO_lms_lect_appl -- 강의 -> 수강신청
    FOREIGN KEY (
      lno -- 강의번호
    )
    REFERENCES lms_lecture ( -- 강의
      lno -- 강의번호
    );

-- 수강신청
ALTER TABLE lms_lect_appl
  ADD CONSTRAINT FK_lms_stud_TO_lms_lect_appl -- 수강생 -> 수강신청
    FOREIGN KEY (
      sno -- 수강생번호
    )
    REFERENCES lms_stud ( -- 수강생
      sno -- 수강생번호
    );

-- 강사배정
ALTER TABLE lms_lect_tchr
  ADD CONSTRAINT FK_lms_lecture_TO_lms_lect_tchr -- 강의 -> 강사배정
    FOREIGN KEY (
      lno -- 강의번호
    )
    REFERENCES lms_lecture ( -- 강의
      lno -- 강의번호
    );

-- 강사배정
ALTER TABLE lms_lect_tchr
  ADD CONSTRAINT FK_lms_tchr_TO_lms_lect_tchr -- 강사 -> 강사배정
    FOREIGN KEY (
      tno -- 강사번호
    )
    REFERENCES lms_tchr ( -- 강사
      tno -- 강사번호
    );