1. memo 테이블 구조

1) 테이블 제작: /WebContent/doc/memo.sql
- NOT NULL   : INSERT시 필수 입력, 생략 불가능
- DEFAULT 0  : INSERT시 값을 입력하지 않으면 0이 추가됨.
- DEFAULT 'A': INSERT시 값을 입력하지 않으면 'A'가 추가됨.
- NUMBER(6)  : int 계열, -999999 ~ +999999, 나머지 바이트 재사용
- VARCHAR(30): String 계열, 문자열 저장, 가변길이 문자열, 빈공간 재사용,
               한글 15자, 영숫자는 30자
- DATE       : 날짜


-- 임시 영역으로 테이블 삭제, 복구 가능한 휴지통으로 삭제
DROP TABLE memo;

-- 테이블을 복구 불가능 하도록 삭제
DROP TABLE memo PURGE;

-- 테이블 생성
CREATE TABLE memo(
    memono     NUMBER(6)     NOT NULL, -- 일련번호, -999999 ~ +999999
    title      VARCHAR(50)   NOT NULL, -- 메모 제목, 한글 25자
    content    VARCHAR(4000) NOT NULL, -- 내용 
    wdate      DATE          NOT NULL, -- 등록 날짜 
    viewcnt    NUMBER(5)     DEFAULT 0, -- 조회수 
    PRIMARY KEY(memono) 
);



2) SEQUENCE (MySQL: AUTO_INCREMENT 동일한 기능)
- 레코드는 고유하게 구분되어야하는 제약이 있으며
  고유한 값을 저장하기위해 데이터를 구분값으로 사용하는 경우가 있고
  Sequence즉 일련번호처럼 오라클에서 생성되는 값을 사용하는 경우도 있음.

  START WITH 0: 일련번호 1부터 시작
  INCREMENT BY 1: 일련번호를 1 씩 증가
  CACHE 2: 시퀀스를 테이블에 저장함으로 자주 Update가 발생함으로
           이것을 줄이기위해 메모리에서 일련번호를 증가시키며
           CACHE 명령을 이용해 증가 단위를 정의할 수 있음, 
           메모리에서 일련번호 증가, 처리 속도 향상,
           기본값은 20임, INSERT가 많이 발생하면 기본값을 길게 해줌 
  
  MySQL: memono   INT NOT NULL AUTO_INCREMENT PRIMARY KEY


-- SEQUENCE 삭제
DROP SEQUENCE memo_seq;

-- SEQUENCE 생성
CREATE SEQUENCE memo_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0
CACHE 2; -- 2개임



-- create
-- memo_seq.nextval: 일련번호 자동 생성하여 할당
--                   (MySQL은 컬럼명을 명시하지 않음).
-- sysdate: 현재 날짜와 시간(MySQL: now())
INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '오늘의 메모1', '오늘의 메모1 내용', sysdate);

INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '오늘의 메모2', '오늘의 메모2 내용', sysdate);

INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '오늘의 메모3', '오늘의 메모3 내용', sysdate);

INSERT INTO memo
VALUES(memo_seq.nextval, '오늘의 메모4', '오늘의 메모4 내용', sysdate);



-- ERROR
-- 한글 1자는 사이즈 2로 처리됨, 실제 공간은 50이나 아래는 52바이트임으로 ERROR
INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '가나다라마바사아자카타가나다라마가나다라마바사아자카', 'TEST', sysdate);


-- select list, 자주 읽는 메모 먼저 출력 
-- DESC: Descending, ASC: Ascending
SELECT memono, title, content, wdate, viewcnt
FROM memo 
ORDER BY memono ASC;
------------------------------------------------------------

UPDATE memo
SET title='목요일', content='친구만나기'
WHERE memono=13;

DELETE FROM memo
WHERE memono= 20;

-- 조회수 증가
UPDATE memo
SET viewcnt = viewcnt + 1
WHERE memono = 1;