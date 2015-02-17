CREATE TABLE madmin ( -- 관리자
    id       VARCHAR(10) NOT NULL, -- 아이디, 중복 안됨, 레코드를 구분하는 컬럼 
    passwd   VARCHAR(20) NOT NULL, -- 패스워드, 영숫자 조합
    mname    VARCHAR(20) NOT NULL, -- 성명, 한글 10자 저장 가능
    tel      VARCHAR(14)     NULL, -- 전화번호
    email    VARCHAR(50) NOT NULL UNIQUE, -- 전자우편 주소, 중복 안됨
    zipcode  VARCHAR(7)      NULL, -- 우편번호, 101-101
    address1 VARCHAR(50)     NULL, -- 주소 1
    address2 VARCHAR(50)     NULL, -- 주소 2
    position VARCHAR(20) NOT NULL, -- 직급
    mdate    DATE        NOT NULL, -- 입사일    
    fname    VARCHAR(50) DEFAULT 'member.jpg', -- 회원 사진
    PRIMARY KEY (id)               -- 한번 등록된 id는 중복 안됨
);

DROP TABLE madmin PURGE;

CREATE TABLE employee ( -- 멤버
    id       VARCHAR(10) NOT NULL, -- 아이디, 중복 안됨, 레코드를 구분하는 컬럼 
    passwd   VARCHAR(20) NOT NULL, -- 패스워드, 영숫자 조합
    mname    VARCHAR(20) NOT NULL, -- 성명, 한글 10자 저장 가능
    tel      VARCHAR(14)     NULL, -- 전화번호
    email    VARCHAR(50) NOT NULL UNIQUE, -- 전자우편 주소, 중복 안됨
    zipcode  VARCHAR(7)      NULL, -- 우편번호, 101-101
    address1 VARCHAR(50)     NULL, -- 주소 1
    address2 VARCHAR(50)     NULL, -- 주소 2
    position VARCHAR(20) NOT NULL, -- 직급
    mdate    DATE        NOT NULL, -- 입사일    
    fname    VARCHAR(50) DEFAULT 'member.jpg', -- 회원 사진
    mtype    NUMBER(9)   NOT NULL, -- 1:직원, 2: 일반 회원 3:판매자  
    PRIMARY KEY (id)               -- 한번 등록된 id는 중복 안됨
);

CREATE TABLE product ( -- 물품
    productno NUMBER(7)    NOT NULL,  -- 품번
    pname     VARCHAR(20)  NOT NULL,  -- 품명
    price     NUMBER(10)   NOT NULL,  -- 판매가
    rdate     DATE         NOT NULL,  -- 입고일
    fname     VARCHAR(50)  DEFAULT 'product.jpg', -- 물품 사진
    mcount    NUMBER(10)   NOT NULL,  -- 수량
    content   VARCHAR(3000) NOT NULL,  -- 상품 설명
    volume    VARCHAR(10)  NOT NULL,  -- 용량
    mtype     VARCHAR(20)  NOT NULL,  -- 피부 타입  -- 판매 회사-> 판매자(FK):멤버에서 가져옴
    morder    NUMBER(10)   NOT NULL,  -- 주문수량
    etc       VARCHAR(200) NOT NULL,  -- 비고  
    PRIMARY KEY (productno)  
);

CREATE TABLE pgroup ( -- 화장품 분류
    pgroupno  NUMBER(7)    NOT NULL,  -- 분류 번호 -- 스킨케어, 로션, 색조~~~~
    pgname    VARCHAR(20)  NOT NULL,  -- 분류명
    PRIMARY KEY (pgroupno)  
);

CREATE TABLE qna ( -- Q&A
  qnano   NUMBER(7)     NOT NULL,  -- 질문글 번호, -9999999 ~ +9999999
  wname   VARCHAR(20)   NOT NULL,  -- 글쓴이
  title   VARCHAR(100)  NOT NULL,  -- 제목(*)
  content VARCHAR(4000) NOT NULL,  -- 글 내용
  passwd  VARCHAR(15)   NOT NULL,  -- 비밀 번호
  viewcnt NUMBER(5)     DEFAULT 0, -- 조회수, 기본값 사용
  wdate   DATE          NOT NULL,  -- 등록 날짜, sysdate
  grpno   NUMBER(7)     DEFAULT 0, -- 부모글 번호
  indent  NUMBER(2)     DEFAULT 0, -- 답변여부
  ansnum  NUMBER(5)     DEFAULT 0, -- 답변 순서
  PRIMARY KEY (qnano) 
);

CREATE TABLE rdata ( -- 자료실 -- 보충 해야함 -- 자유게시판처럼.... --로그인 한 멤버에 한에서 가능하도록
  rdatano   NUMBER(7)     NOT NULL,  -- 자료 번호, -9999999 ~ +9999999
  title     VARCHAR(100)  NOT NULL,  -- 제목(*), 한글50자, 영숫자 100자
  rdate     DATE          NOT NULL,  -- 등록 날짜, sysdate
  fname     VARCHAR(50)   NULL,      -- 원본 파일명, tulip01.jpg
  fsize     NUMBER(9)     DEFAULT 0 NOT NULL,  -- 원본 파일 사이즈
  content   VARCHAR(4000) NOT NULL,  -- 글 내용
  passwd    VARCHAR(15)   NOT NULL,  -- 비밀 번호  
  viewcnt   NUMBER(5)     DEFAULT 0, -- 조회수, 기본값 사용
  grpno     NUMBER(7)     DEFAULT 0, -- 부모글 번호
  indent    NUMBER(2)     DEFAULT 0, -- 답변여부
  ansnum    NUMBER(5)     DEFAULT 0, -- 답변 순서  
  PRIMARY KEY (rdatano) 
);


CREATE TABLE notice ( -- 공지사항 -- 관리자 전용
  notno   NUMBER(7)     NOT NULL,  -- 자료 번호, -9999999 ~ +9999999
  title     VARCHAR(100)  NOT NULL,  -- 제목(*), 한글50자, 영숫자 100자
  rdate     DATE          NOT NULL,  -- 등록 날짜, sysdate
  fname     VARCHAR(50)   NULL,      -- 원본 파일명, tulip01.jpg
  fsize     NUMBER(9)     DEFAULT 0 NOT NULL,  -- 원본 파일 사이즈
  content   VARCHAR(4000) NOT NULL,  -- 글 내용
  viewcnt   NUMBER(5)     DEFAULT 0, -- 조회수, 기본값 사용
  PRIMARY KEY (notno) 
);

CREATE TABLE import ( -- 입고
  imno   NUMBER(7)        NOT NULL,   -- 입고물품번호  -9999999 ~ +9999999 - 품명(FK)
  idate      DATE          NOT NULL,  -- 입고일
  icount     NUMBER(10)    NOT NULL,  -- 입고량
  iprice     NUMBER(10)   NOT NULL,   -- 매입가   -- 사원(FK)
  PRIMARY KEY (imno) 
);

CREATE TABLE export ( -- 출고
  exno       NUMBER(7)        NOT NULL,  -- 입고물품번호  -9999999 ~ +9999999 - 품명(FK)
  edate      DATE          NOT NULL,     -- 입고일
  ecount     NUMBER(10)    NOT NULL,     -- 입고량
  factory    VARCHAR(50)   NULL,         -- 출고지   -- 사원(FK), 품목의 가격과FK
  PRIMARY KEY (exno) 
);


CREATE TABLE ereturn ( -- 반품
  erno       NUMBER(7)      NOT NULL,    -- 입고물품번호  -9999999 ~ +9999999 - 품명(FK)
  erdate     DATE           NOT NULL,    -- 입고일
  ercount     NUMBER(10)    NOT NULL,    -- 입고량   -- 사원(FK)
  factory    VARCHAR(50)   NULL,         -- 출고지
  PRIMARY KEY (erno) 
);


CREATE TABLE product ( -- 판매자  -- 나중에 수정
    salerno   NUMBER(7)    NOT NULL,  -- 판매자 번호
    sname     VARCHAR(20)  NOT NULL,  -- 품명
    price     NUMBER(10)   NOT NULL,  -- 단가
    rdate     DATE         NOT NULL,  -- 입고일
    fname     VARCHAR(50)  DEFAULT 'product.jpg', -- 물품 사진
    count     NUMBER(10)   NOT NULL,  -- 수량
    content   VARCHAR(300) NOT NULL,  -- 상품 설명
    volume    VARCHAR(10)  NOT NULL,  -- 용량
    mtype     VARCHAR(10)  NOT NULL,  -- 피부 타입  -- 판매 회사-> 판매자(FK)
    morder    NUMBER(10)   NOT NULL,  -- 주문수량
    etc       VARCHAR(200) NOT NULL,  -- 비고 
    PRIMARY KEY (productno)  
);


--판매 업체 지도는 협력업체 링크로 해도 됨,