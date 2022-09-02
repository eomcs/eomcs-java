-- 수강신청
DROP TABLE IF EXISTS ems_application RESTRICT;

-- 강의
DROP TABLE IF EXISTS ems_lecture RESTRICT;

-- 강사
DROP TABLE IF EXISTS ems_teacher RESTRICT;

-- 강의강사
DROP TABLE IF EXISTS ems_teacher_lecture RESTRICT;

-- 강의실
DROP TABLE IF EXISTS ems_room RESTRICT;

-- 매니저
DROP TABLE IF EXISTS ems_manager RESTRICT;

-- 학생
DROP TABLE IF EXISTS ems_student RESTRICT;

-- 학교
DROP TABLE IF EXISTS ems_school RESTRICT;

-- 부서
DROP TABLE IF EXISTS ems_department RESTRICT;

-- 직위
DROP TABLE IF EXISTS ems_position RESTRICT;

-- 은행
DROP TABLE IF EXISTS ems_bank RESTRICT;

-- 교육센터
DROP TABLE IF EXISTS ems_center RESTRICT;

-- 기본주소
DROP TABLE IF EXISTS ems_address RESTRICT;

-- 회원
DROP TABLE IF EXISTS ems_member RESTRICT;

-- 수강신청
CREATE TABLE ems_application (
  apno  INTEGER  NOT NULL COMMENT '수강신청번호', -- 수강신청번호
  lno   INTEGER  NOT NULL COMMENT '강의번호', -- 강의번호
  mno   INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  cdate DATETIME NOT NULL COMMENT '신청일' -- 신청일
)
COMMENT '수강신청';

-- 수강신청
ALTER TABLE ems_application
  ADD CONSTRAINT PK_ems_application -- 수강신청 기본키
    PRIMARY KEY (
      apno -- 수강신청번호
    );

ALTER TABLE ems_application
  MODIFY COLUMN apno INTEGER NOT NULL AUTO_INCREMENT COMMENT '수강신청번호';

-- 강의
CREATE TABLE ems_lecture (
  lno   INTEGER      NOT NULL COMMENT '강의번호', -- 강의번호
  title VARCHAR(255) NOT NULL COMMENT '강의명', -- 강의명
  COL   MEDIUMTEXT   NULL     COMMENT '내용', -- 내용
  sdate DATE         NOT NULL COMMENT '시작일', -- 시작일
  edate DATE         NOT NULL COMMENT '종료일', -- 종료일
  qty   INTEGER      NOT NULL COMMENT '수용인원', -- 수용인원
  rno   INTEGER      NULL     COMMENT '강의실번호', -- 강의실번호
  mno   INTEGER      NULL     COMMENT '회원번호' -- 회원번호
)
COMMENT '강의';

-- 강의
ALTER TABLE ems_lecture
  ADD CONSTRAINT PK_ems_lecture -- 강의 기본키
    PRIMARY KEY (
      lno -- 강의번호
    );

ALTER TABLE ems_lecture
  MODIFY COLUMN lno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의번호';

-- 강사
CREATE TABLE ems_teacher (
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  bno      INTEGER      NOT NULL COMMENT '은행번호', -- 은행번호
  account  VARCHAR(20)  NOT NULL COMMENT '계좌번호', -- 계좌번호
  hour_pay INTEGER      NOT NULL COMMENT '시강료', -- 시강료
  ano      INTEGER      NOT NULL COMMENT '기본주소번호', -- 기본주소번호
  det_addr VARCHAR(255) NOT NULL COMMENT '상세주소' -- 상세주소
)
COMMENT '강사';

-- 강사
ALTER TABLE ems_teacher
  ADD CONSTRAINT PK_ems_teacher -- 강사 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 강의강사
CREATE TABLE ems_teacher_lecture (
  lno INTEGER NOT NULL COMMENT '강의번호', -- 강의번호
  mno INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '강의강사';

-- 강의강사
ALTER TABLE ems_teacher_lecture
  ADD CONSTRAINT PK_ems_teacher_lecture -- 강의강사 기본키
    PRIMARY KEY (
      lno, -- 강의번호
      mno  -- 회원번호
    );

-- 강의실
CREATE TABLE ems_room (
  rno   INTEGER     NOT NULL COMMENT '강의실번호', -- 강의실번호
  cno   INTEGER     NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  title VARCHAR(50) NOT NULL COMMENT '강의실' -- 강의실
)
COMMENT '강의실';

-- 강의실
ALTER TABLE ems_room
  ADD CONSTRAINT PK_ems_room -- 강의실 기본키
    PRIMARY KEY (
      rno -- 강의실번호
    );

ALTER TABLE ems_room
  MODIFY COLUMN rno INTEGER NOT NULL AUTO_INCREMENT COMMENT '강의실번호';

-- 매니저
CREATE TABLE ems_manager (
  mno  INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  ctel VARCHAR(30) NULL     COMMENT '회사전화', -- 회사전화
  cfax VARCHAR(30) NULL     COMMENT '회사팩스', -- 회사팩스
  dno  INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
  pno  INTEGER     NOT NULL COMMENT '직위번호' -- 직위번호
)
COMMENT '매니저';

-- 매니저
ALTER TABLE ems_manager
  ADD CONSTRAINT PK_ems_manager -- 매니저 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 학생
CREATE TABLE ems_student (
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  work     BOOLEAN      NOT NULL COMMENT '재직여부', -- 재직여부
  ano      INTEGER      NULL     COMMENT '기본주소번호', -- 기본주소번호
  det_addr VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  bno      INTEGER      NULL     COMMENT '은행번호', -- 은행번호
  account  VARCHAR(20)  NULL     COMMENT '계좌번호' -- 계좌번호
)
COMMENT '학생';

-- 학생
ALTER TABLE ems_student
  ADD CONSTRAINT PK_ems_student -- 학생 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 학교
CREATE TABLE ems_school (
  sno   INTEGER     NOT NULL COMMENT '학교번호', -- 학교번호
  title VARCHAR(50) NOT NULL COMMENT '학교명' -- 학교명
)
COMMENT '학교';

-- 학교
ALTER TABLE ems_school
  ADD CONSTRAINT PK_ems_school -- 학교 기본키
    PRIMARY KEY (
      sno -- 학교번호
    );

-- 학교 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_school
  ON ems_school ( -- 학교
    title ASC -- 학교명
  );

ALTER TABLE ems_school
  MODIFY COLUMN sno INTEGER NOT NULL AUTO_INCREMENT COMMENT '학교번호';

-- 부서
CREATE TABLE ems_department (
  dno   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
  title VARCHAR(50) NOT NULL COMMENT '부서명' -- 부서명
)
COMMENT '부서';

-- 부서
ALTER TABLE ems_department
  ADD CONSTRAINT PK_ems_department -- 부서 기본키
    PRIMARY KEY (
      dno -- 부서번호
    );

-- 부서 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_department
  ON ems_department ( -- 부서
    title ASC -- 부서명
  );

ALTER TABLE ems_department
  MODIFY COLUMN dno INTEGER NOT NULL AUTO_INCREMENT COMMENT '부서번호';

-- 직위
CREATE TABLE ems_position (
  pno   INTEGER     NOT NULL COMMENT '직위번호', -- 직위번호
  title VARCHAR(50) NOT NULL COMMENT '직위명' -- 직위명
)
COMMENT '직위';

-- 직위
ALTER TABLE ems_position
  ADD CONSTRAINT PK_ems_position -- 직위 기본키
    PRIMARY KEY (
      pno -- 직위번호
    );

-- 직위 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_position
  ON ems_position ( -- 직위
    title ASC -- 직위명
  );

ALTER TABLE ems_position
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '직위번호';

-- 은행
CREATE TABLE ems_bank (
  bno   INTEGER     NOT NULL COMMENT '은행번호', -- 은행번호
  title VARCHAR(50) NOT NULL COMMENT '은행명' -- 은행명
)
COMMENT '은행';

-- 은행
ALTER TABLE ems_bank
  ADD CONSTRAINT PK_ems_bank -- 은행 기본키
    PRIMARY KEY (
      bno -- 은행번호
    );

-- 은행 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_bank
  ON ems_bank ( -- 은행
    title ASC -- 은행명
  );

ALTER TABLE ems_bank
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '은행번호';

-- 교육센터
CREATE TABLE ems_center (
  cno      INTEGER      NOT NULL COMMENT '교육센터번호', -- 교육센터번호
  title    VARCHAR(50)  NOT NULL COMMENT '센터명', -- 센터명
  comp_id  VARCHAR(20)  NOT NULL COMMENT '사업자번호', -- 사업자번호
  ano      INTEGER      NOT NULL COMMENT '기본주소번호', -- 기본주소번호
  det_addr VARCHAR(255) NOT NULL COMMENT '상세주소', -- 상세주소
  tel      VARCHAR(30)  NOT NULL COMMENT '대표전화', -- 대표전화
  fax      VARCHAR(30)  NULL     COMMENT '팩스', -- 팩스
  homepage VARCHAR(255) NULL     COMMENT '홈페이지' -- 홈페이지
)
COMMENT '교육센터';

-- 교육센터
ALTER TABLE ems_center
  ADD CONSTRAINT PK_ems_center -- 교육센터 기본키
    PRIMARY KEY (
      cno -- 교육센터번호
    );

-- 교육센터 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_center
  ON ems_center ( -- 교육센터
    title ASC -- 센터명
  );

ALTER TABLE ems_center
  MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '교육센터번호';

-- 기본주소
CREATE TABLE ems_address (
  ano      INTEGER      NOT NULL COMMENT '기본주소번호', -- 기본주소번호
  postno   VARCHAR(6)   NOT NULL COMMENT '우편번호', -- 우편번호
  bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '기본주소';

-- 기본주소
ALTER TABLE ems_address
  ADD CONSTRAINT PK_ems_address -- 기본주소 기본키
    PRIMARY KEY (
      ano -- 기본주소번호
    );

ALTER TABLE ems_address
  MODIFY COLUMN ano INTEGER NOT NULL AUTO_INCREMENT COMMENT '기본주소번호';

-- 회원
CREATE TABLE ems_member (
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name     VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  email    VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  password VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  tel      VARCHAR(30)  NOT NULL COMMENT '휴대전화', -- 휴대전화
  grade    VARCHAR(50)  NULL     COMMENT '최종학력', -- 최종학력
  sno      INTEGER      NULL     COMMENT '학교번호', -- 학교번호
  major    VARCHAR(50)  NULL     COMMENT '전공' -- 전공
)
COMMENT '회원';

-- 회원
ALTER TABLE ems_member
  ADD CONSTRAINT PK_ems_member -- 회원 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_ems_member
  ON ems_member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_ems_member2
  ON ems_member ( -- 회원
    tel ASC -- 휴대전화
  );

-- 회원 인덱스
CREATE INDEX IX_ems_member
  ON ems_member( -- 회원
    name ASC -- 이름
  );

ALTER TABLE ems_member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 수강신청
ALTER TABLE ems_application
  ADD CONSTRAINT FK_ems_lecture_TO_ems_application -- 강의 -> 수강신청
    FOREIGN KEY (
      lno -- 강의번호
    )
    REFERENCES ems_lecture ( -- 강의
      lno -- 강의번호
    );

-- 수강신청
ALTER TABLE ems_application
  ADD CONSTRAINT FK_ems_student_TO_ems_application -- 학생 -> 수강신청
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_student ( -- 학생
      mno -- 회원번호
    );

-- 강의
ALTER TABLE ems_lecture
  ADD CONSTRAINT FK_ems_room_TO_ems_lecture -- 강의실 -> 강의
    FOREIGN KEY (
      rno -- 강의실번호
    )
    REFERENCES ems_room ( -- 강의실
      rno -- 강의실번호
    );

-- 강의
ALTER TABLE ems_lecture
  ADD CONSTRAINT FK_ems_manager_TO_ems_lecture -- 매니저 -> 강의
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_manager ( -- 매니저
      mno -- 회원번호
    );

-- 강사
ALTER TABLE ems_teacher
  ADD CONSTRAINT FK_ems_bank_TO_ems_teacher -- 은행 -> 강사
    FOREIGN KEY (
      bno -- 은행번호
    )
    REFERENCES ems_bank ( -- 은행
      bno -- 은행번호
    );

-- 강사
ALTER TABLE ems_teacher
  ADD CONSTRAINT FK_ems_address_TO_ems_teacher -- 기본주소 -> 강사
    FOREIGN KEY (
      ano -- 기본주소번호
    )
    REFERENCES ems_address ( -- 기본주소
      ano -- 기본주소번호
    );

-- 강사
ALTER TABLE ems_teacher
  ADD CONSTRAINT FK_ems_member_TO_ems_teacher -- 회원 -> 강사
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_member ( -- 회원
      mno -- 회원번호
    );

-- 강의강사
ALTER TABLE ems_teacher_lecture
  ADD CONSTRAINT FK_ems_lecture_TO_ems_teacher_lecture -- 강의 -> 강의강사
    FOREIGN KEY (
      lno -- 강의번호
    )
    REFERENCES ems_lecture ( -- 강의
      lno -- 강의번호
    );

-- 강의강사
ALTER TABLE ems_teacher_lecture
  ADD CONSTRAINT FK_ems_teacher_TO_ems_teacher_lecture -- 강사 -> 강의강사
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_teacher ( -- 강사
      mno -- 회원번호
    );

-- 강의실
ALTER TABLE ems_room
  ADD CONSTRAINT FK_ems_center_TO_ems_room -- 교육센터 -> 강의실
    FOREIGN KEY (
      cno -- 교육센터번호
    )
    REFERENCES ems_center ( -- 교육센터
      cno -- 교육센터번호
    );

-- 매니저
ALTER TABLE ems_manager
  ADD CONSTRAINT FK_ems_department_TO_ems_manager -- 부서 -> 매니저
    FOREIGN KEY (
      dno -- 부서번호
    )
    REFERENCES ems_department ( -- 부서
      dno -- 부서번호
    );

-- 매니저
ALTER TABLE ems_manager
  ADD CONSTRAINT FK_ems_position_TO_ems_manager -- 직위 -> 매니저
    FOREIGN KEY (
      pno -- 직위번호
    )
    REFERENCES ems_position ( -- 직위
      pno -- 직위번호
    );

-- 매니저
ALTER TABLE ems_manager
  ADD CONSTRAINT FK_ems_member_TO_ems_manager -- 회원 -> 매니저
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_member ( -- 회원
      mno -- 회원번호
    );

-- 학생
ALTER TABLE ems_student
  ADD CONSTRAINT FK_ems_bank_TO_ems_student -- 은행 -> 학생
    FOREIGN KEY (
      bno -- 은행번호
    )
    REFERENCES ems_bank ( -- 은행
      bno -- 은행번호
    );

-- 학생
ALTER TABLE ems_student
  ADD CONSTRAINT FK_ems_address_TO_ems_student -- 기본주소 -> 학생
    FOREIGN KEY (
      ano -- 기본주소번호
    )
    REFERENCES ems_address ( -- 기본주소
      ano -- 기본주소번호
    );

-- 학생
ALTER TABLE ems_student
  ADD CONSTRAINT FK_ems_member_TO_ems_student -- 회원 -> 학생
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES ems_member ( -- 회원
      mno -- 회원번호
    );

-- 교육센터
ALTER TABLE ems_center
  ADD CONSTRAINT FK_ems_address_TO_ems_center -- 기본주소 -> 교육센터
    FOREIGN KEY (
      ano -- 기본주소번호
    )
    REFERENCES ems_address ( -- 기본주소
      ano -- 기본주소번호
    );

-- 회원
ALTER TABLE ems_member
  ADD CONSTRAINT FK_ems_school_TO_ems_member -- 학교 -> 회원
    FOREIGN KEY (
      sno -- 학교번호
    )
    REFERENCES ems_school ( -- 학교
      sno -- 학교번호
    );