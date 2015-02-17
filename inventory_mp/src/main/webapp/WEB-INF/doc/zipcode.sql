DROP TABLE zipcode CASCADE CONSTRAINTS;

CREATE TABLE zipcode (
    zipcode CHAR(7)      NOT NULL, -- 우편번호
    sido    VARCHAR(10)  NOT NULL, -- 시, 도
    gugun   VARCHAR(20)  NOT NULL, -- 구, 군
    dongli  VARCHAR(100) NOT NULL, -- 동, 리
    etc     VARCHAR(30)      NULL  -- 세부 주소
);


insert into zipcode values('405-807','인천','남동구','간석1동','272∼293번지');
insert into zipcode values('405-807','인천','남동구','간석1동','308∼343');
insert into zipcode values('405-809','인천','남동구','간석1동','345∼354');
insert into zipcode values('405-809','인천','남동구','간석1동','399∼525');
insert into zipcode values('405-811','인천','남동구','간석1동','531∼535');
insert into zipcode values('405-811','인천','남동구','간석1동','537∼570');
insert into zipcode values('405-814','인천','남동구','간석1동','896∼908');
insert into zipcode values('405-231','인천','남동구','간석1동',' ');
insert into zipcode values('405-705','인천','남동구','간석1동 극동아파트',' ');
insert into zipcode values('405-706','인천','남동구','간석1동 금호아파트',' ');
insert into zipcode values('405-708','인천','남동구','간석1동 범양아파트',' ');
insert into zipcode values('405-709','인천','남동구','간석1동 송림아파트',' ');
insert into zipcode values('405-710','인천','남동구','간석1동 영남아파트',' ');
insert into zipcode values('405-713','인천','남동구','간석1동 이화아파트',' ');
insert into zipcode values('405-714','인천','남동구','간석1동 한진아파트',' ');
insert into zipcode values('405-715','인천','남동구','간석1동 현광아파트',' ');
insert into zipcode values('405-716','인천','남동구','간석1동 현대아파트',' ');
insert into zipcode values('405-802','인천','남동구','간석2동','126∼169번지');
insert into zipcode values('405-803','인천','남동구','간석2동','173∼210');
insert into zipcode values('405-808','인천','남동구','간석2동','294∼307');
insert into zipcode values('405-808','인천','남동구','간석2동','344');
insert into zipcode values('405-232','인천','남동구','간석2동',' ');
insert into zipcode values('405-717','인천','남동구','간석2동 간석주공맨션','(53∼74동)');
insert into zipcode values('405-751','인천','남동구','간석2동 간석주공아파트','(1∼52동)');
insert into zipcode values('405-718','인천','남동구','간석2동 우신아파트',' ');
insert into zipcode values('405-800','인천','남동구','간석3동','1∼71번지');
insert into zipcode values('405-801','인천','남동구','간석3동','72∼125');
insert into zipcode values('405-801','인천','남동구','간석3동','170∼172');
insert into zipcode values('405-805','인천','남동구','간석3동','224∼225');
insert into zipcode values('405-812','인천','남동구','간석3동','768∼775');
insert into zipcode values('405-815','인천','남동구','간석3동','909∼921');
insert into zipcode values('405-233','인천','남동구','간석3동',' ');
insert into zipcode values('405-701','인천','남동구','간석3동 가천길대학',' ');
insert into zipcode values('405-719','인천','남동구','간석3동 두진아파트',' ');
insert into zipcode values('405-720','인천','남동구','간석3동 신한아파트',' ');
insert into zipcode values('405-723','인천','남동구','간석3동 지하철공사',' ');
insert into zipcode values('405-724','인천','남동구','간석3동 태화아파트',' ');
insert into zipcode values('405-725','인천','남동구','간석3동 팬더아파트',' ');
insert into zipcode values('405-804','인천','남동구','간석4동','211∼223번지');
insert into zipcode values('405-806','인천','남동구','간석4동','226∼271');
insert into zipcode values('405-806','인천','남동구','간석4동','355∼398');
insert into zipcode values('405-810','인천','남동구','간석4동','526∼530');
insert into zipcode values('405-810','인천','남동구','간석4동','536');
insert into zipcode values('405-810','인천','남동구','간석4동','571∼767');
insert into zipcode values('405-813','인천','남동구','간석4동','776∼895');
insert into zipcode values('405-234','인천','남동구','간석4동',' ');
insert into zipcode values('405-752','인천','남동구','간석4동 우성아파트',' ');
insert into zipcode values('405-726','인천','남동구','간석4동 주안주공아파트',' ');
insert into zipcode values('405-230','인천','남동구','간석동',' ');


SELECT COUNT(*) as cnt 
FROM zipcode 
ORDER BY zipcode;


SELECT zipcode, sido, gugun, dongli, etc
FROM zipcode
WHERE (dongli LIKE '%간석4동%')
ORDER BY sido, gugun, dongli;


    
 
[02] SubQuery

가. 정의: SQL안에 SQL이 내장(중첩)되어 있는 쿼리


나. 특징:
    1) 레코드가 많은 경우 레코드를 분리해서 DB로부터 추출해야
       하는데 이때 사용하는 쿼리가 Subquery 입니다.

    2) Subquery를 사용하지 않고 레코드 추출시 서버에 따라
       100~200건 정도로 제한해서 읽어오는 서버도 있으나
       절대 권장하지 않음. 
       예) SQL Explorer Tool

    3) 가장 안쪽의 subquery가 먼저 실행되고 바깥쪽의
       서브쿼리가 실행됩니다. 
 
    4) 데이터베이스 서버에 순간 부하는 발생할 수 있으나
       네트워크 트래픽이 감소되며 자바 코드를 줄일 수 있는 장점이
       있습니다. 서버의 메모리를 절약할 수 있다.

    5) 너무 많은 서브쿼리 사용은 DB 서버의 속도를 느리게 함으로
       부하가 걸리지 않는 선에서 적당히 사용해야 합니다.

    6) SubQuery는 View의 대상이 된다. 
 
    7) 서브쿼리와 비슷한 기능으로 MySQL-LIMIT, MS-SQL-TOP이 있음.



1. SubQuery 실습
   SELECT COUNT(*) FROM zipcode;

   COUNT(*)
   --------
         49

       
>>>>> /WebContent/doc/subquery.sql

1) 전체 레코드 출력
   SELECT zipcode, sido, gugun, dongli, etc
   FROM zipcode
   ORDER BY sido, gugun, dongli, etc;
   
   
   
2) SELECT 결과중 상위 5개의 우편번호만 출력
   - rownum 가상 컬럼을 이용합니다.
   - subquery를 사용해야 합니다.   
   


3) rownum 가상 컬럼의 이용: SELECT시 일련번호 생성 
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM zipcode;



4) 정렬시의 rownum 가상 컬럼의 이용
   - 레코드에 rownum 컬럼이 사용되고 난 후 정렬이되어 
     rownum 컬럼의 값이 순차적으로 출력되지 않았습니다.
     
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM zipcode
   ORDER BY sido, gugun, dongli, etc;



5) 정렬을 먼저 한후 결과를 받아 rownum 컬럼을 출력합니다.

   - 메모리상에서는 SELECT 결과는 테이블로 존재함으로
     컬럼을 그대로 받아 rownum과 함께 출력
     
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM (
         SELECT zipcode, sido, gugun, dongli, etc
         FROM zipcode
         ORDER BY sido, gugun, dongli, etc
   );



6) 특정 레코드만 추출, 상위 5개만 추출
   - WHERE 보다 rownum은 나중에 생성됨으로 WHERE문에서 rownum을
     제대로 인식을 못함.


   SELECT zipcode, sido, gugun, dongli, etc, rownum as r
   FROM (
         SELECT zipcode, sido, gugun, dongli, etc
         FROM zipcode
         ORDER BY sido, gugun, dongli, etc
   )
   WHERE r <= 5;



7) 특정 레코드만 추출, 상위 5개만 추출

   - 서브쿼리를 3단으로 합니다.rownum을 r컬럼이 받습니다.
   
   SELECT zipcode, sido, gugun, dongli, etc, r
   FROM(
        SELECT zipcode, sido, gugun, dongli, etc, rownum as r
        FROM (
              SELECT zipcode, sido, gugun, dongli, etc
              FROM zipcode
              ORDER BY sido, gugun, dongli, etc
        )
   )
   WHERE r <= 5;

 ZIPCODE SIDO GUGUN DONGLI ETC       R
 ------- ---- ----- ------ --------- -
 405-231 인천   남동구   간석1동             1
 405-807 인천   남동구   간석1동   272∼293번지 2
 405-807 인천   남동구   간석1동   308∼343   3
 405-809 인천   남동구   간석1동   345∼354   4
 405-809 인천   남동구   간석1동   399∼525   5
 
 
 
8) 특정 레코드만 추출, 상위 6 ~ 10번만 추출

   - 서브쿼리를 3단으로 합니다.rownum을 r컬럼이 받습니다.
   
   SELECT zipcode, sido, gugun, dongli, etc, r
   FROM(
        SELECT zipcode, sido, gugun, dongli, etc, rownum as r
        FROM (
              SELECT zipcode, sido, gugun, dongli, etc
              FROM zipcode
              ORDER BY sido, gugun, dongli, etc
        )
   )
   WHERE r >= 6 and r <= 10;
   

 ZIPCODE SIDO GUGUN DONGLI     ETC     R
 ------- ---- ----- ---------- ------- --
 405-809 인천   남동구   간석1동       399∼525  5
 405-811 인천   남동구   간석1동       531∼535  6
 405-811 인천   남동구   간석1동       537∼570  7
 405-814 인천   남동구   간석1동       896∼908  8
 405-705 인천   남동구   간석1동 극동아파트          9
 405-706 인천   남동구   간석1동 금호아파트         10
 
 
   - ERROR
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM(
        SELECT zipcode, sido, gugun, dongli, etc, rownum
        FROM (
              SELECT zipcode, sido, gugun, dongli, etc
              FROM zipcode
              ORDER BY sido, gugun, dongli, etc
             )
        )
   WHERE rownum >= 5 and rownum <= 10;
    





[과제1] 공지사항 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE ntc(
    ntcno NUMBER(7)     NOT NULL, -- 일련번호
    title    VARCHAR(100)  NOT NULL, -- 제목
    content  VARCHAR(2000) NOT NULL, -- 내용
    writer   VARCHAR(20)   NOT NULL, -- 작성자
    passwd   VARCHAR(15)   NOT NULL, -- 패스워드
    ndate    DATE          NOT NULL, -- 작성일
    PRIMARY KEY(ntcno)               
);  
  
SELECT ntcno, title, content, writer, passwd, ndate, r
   FROM(
        SELECT ntcno, title, content, writer, passwd, ndate, rownum as r
        FROM (
              SELECT ntcno, title, content, writer, passwd, ndate
              FROM ntc
              ORDER BY ntcno
        )
   )
   WHERE r >=1 AND r<=10;



[과제2] 회원 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE memberSub (
    id       VARCHAR(10) NOT NULL, -- 아이디, 중복 안됨
    passwd   VARCHAR(10) NOT NULL, -- 패스워드
    mname    VARCHAR(20) NOT NULL, -- 성명 
    zipcode  VARCHAR(7)      NULL, -- 우편번호, 101-101
    memlevel CHAR(2)     NOT NULL, -- 회원 등급, AA, BA, CA, DA, EA, FA
    memdate  DATE        NOT NULL, -- 회원 가입일
    PRIMARY KEY (id)
); 
 
 
SELECT id, passwd, mname, zipcode, memlevel, memdate, r
   FROM(
        SELECT id, passwd, mname, zipcode, memlevel, memdate, rownum as r
        FROM (
              SELECT id, passwd, mname, zipcode, memlevel, memdate
              FROM memberSub
              ORDER BY id
        )
   )
   WHERE r >=1 AND r<=5;
  
   
   
   
   SELECT id, passwd, mname, zipcode, memlevel, memdate, r
   FROM(
        SELECT id, passwd, mname, zipcode, memlevel, memdate, rownum as r
        FROM (
              SELECT id, passwd, mname, zipcode, memlevel, memdate
              FROM memverSub
              ORDER BY id
        )
   )
   WHERE r >=6 AND r<=10;


[과제3] 설문 제목 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE surveyTitle(
    surveytitleno  NUMBER(3)    NOT NULL,
    title          VARCHAR(100) NOT NULL,
    surveydate     DATE,
    PRIMARY KEY(surveytitleno)
);

SELECT surveytitleno, title, surveydate, r
   FROM(
        SELECT surveytitleno, title, surveydate, rownum as r
        FROM (
              SELECT surveytitleno, title, surveydate
              FROM surveyTitle
              ORDER BY surveytitleno
        )
   )
   WHERE r >=1 AND r<=5;




SELECT surveytitleno, title, surveydate, r
   FROM(
        SELECT surveytitleno, title, surveydate, rownum as r
        FROM (
              SELECT surveytitleno, title, surveydate
              FROM surveyTitle
              ORDER BY surveytitleno
        )
   )
   WHERE r >=6 AND r<=10;



[과제4] 설문 항목 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE survey(
    surveyno      NUMBER(5)    NOT NULL, -- 항목 일련 번호
    surveytitleno NUMBER(5)    NOT NULL, -- 설문 제목 번호
    content       VARCHAR(100) DEFAULT 0 NOT NULL, -- 내용
    cnt           NUMBER(5)    DEFAULT 0 NOT NULL, -- 선택 수
    PRIMARY KEY(surveyno)
);


SELECT surveyno, surveytitleno, content, cnt, r
   FROM(
        SELECT surveyno, surveytitleno, content, cnt, rownum as r
        FROM (
              SELECT surveyno, surveytitleno, content, cnt
              FROM survey
              ORDER BY surveyno
        )
   )
   WHERE r >=1 AND r<=5;
   
   
   
   
SELECT surveyno, surveytitleno, content, cnt, r
   FROM(
        SELECT surveyno, surveytitleno, content, cnt, rownum as r
        FROM (
              SELECT surveyno, surveytitleno, content, cnt
              FROM survey
              ORDER BY surveyno
        )
   )
   WHERE r >=6 AND r<=10;   
   



[과제5] 건의 사항 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE surveyEtc(
    surveyetcno   NUMBER(5)    NOT NULL, -- 항목 일련 번호
    surveytitleno NUMBER(5)    NOT NULL, -- 설문 제목 번호
    content       VARCHAR(200) NOT NULL, -- 내용
    passwd        VARCHAR(10)  NOT NULL, -- 선택 수
    PRIMARY KEY(surveyetcno)
);


SELECT surveyetcno, surveytitleno, content, passwd, r
   FROM(
        SELECT surveyetcno, surveytitleno, content, passwd, rownum as r
        FROM (
              SELECT surveyetcno, surveytitleno, content, passwd
              FROM surveyEtc
              ORDER BY surveyetcno
        )
   )
   WHERE r >=1 AND r<=5;





SELECT surveyetcno, surveytitleno, content,passwd, r
   FROM(
        SELECT surveyetcno, surveytitleno, content, passwd, rownum as r
        FROM (
              SELECT surveyetcno, surveytitleno, content, passwd
              FROM surveyEtc
              ORDER BY surveyetcno
        )
   )
   WHERE r >=6 AND r<=10; 
   
   
   
   
   

[과제6] 방명록 테이블에서 레코드를 상위 기준으로 1~ 5번째, 6~10번째
레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE guestbook(
    guestbookcno  NUMBER(5)    NOT NULL, -- 방명록 일련 번호
    content       VARCHAR(500) NOT NULL, -- 내용
    passwd        VARCHAR(20)  NOT NULL, -- 패스워드
    rdate         DATE         NOT NULL, -- 등록일  
    PRIMARY KEY(guestbookcno)
);

SELECT guestbookcno, content, passwd, rdate, r
   FROM(
        SELECT guestbookcno, content, passwd, rdate, rownum as r
        FROM (
              SELECT guestbookcno, content, passwd, rdate
              FROM guestbook
              ORDER BY guestbookcno
        )
   )
   WHERE r >=1 AND r<=5;




SELECT guestbookcno, content, passwd, rdate, r
   FROM(
        SELECT guestbookcno, content, passwd, rdate, rownum as r
        FROM (
              SELECT guestbookcno, content, passwd, rdate
              FROM guestbook
              ORDER BY guestbookcno
        )
   )
   WHERE r >=6 AND r<=10;


    
[과제7] 배낚시 예약 시스템 테이블에서 레코드를 상위 기준으로
        1~ 5번째, 6~10번째 레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE fishing(
    fishingno     NUMBER(5)    NOT NULL, -- 배낚시 예약 일련 번호
    fishcount     NUMBER(5)    NOT NULL, -- 배낚시 인원                                   
    fishtime      NUMBER(5)    NOT NULL, -- 배낚시 시간
    rdate         DATE         NOT NULL, -- 접수 날짜
    price         NUMBER(7)    NOT NULL, -- 등록일  
    PRIMARY KEY(fishingno)
);
    


SELECT fishingno, fishcount, fishtime, rdate, price, r
   FROM(
        SELECT fishingno, fishcount, fishtime, rdate, price, rownum as r
        FROM (
              SELECT fishingno, fishcount, fishtime, rdate, price
              FROM fishing
              ORDER BY fishingno
        )
   )
   WHERE r >=1 AND r<=5;
   
   
    

SELECT fishingno, fishcount, fishtime, rdate, price, r
   FROM(
        SELECT fishingno, fishcount, fishtime, rdate, price, rownum as r
        FROM (
              SELECT fishingno, fishcount, fishtime, rdate, price
              FROM fishing
              ORDER BY fishingno
        )
   )
   WHERE r >=6 AND r<=10;
   
   
   
   

[과제8] 도시락 주문 시스템 테이블에서 레코드를 상위 기준으로
        1~ 5번째, 6~10번째 레코드를 SELECT하는 서브쿼리를 생성하세요.

CREATE TABLE lunch(
    lunchno  NUMBER(5)    NOT NULL, -- 방명록 일련 번호
    title    VARCHAR(30)  NOT NULL, -- 도시락 제목
    content  VARCHAR(500) NOT NULL, -- 도시락 상세 설명
    image1   VARCHAR(30)  NOT NULL, -- 도시락 이미지1
    image2   VARCHAR(30)  NOT NULL, -- 도시락 이미지2
    rdate    DATE         NOT NULL, -- 등록일  
    PRIMARY KEY(lunchno)
);
    

SELECT lunchno, title, content, image1, image2, rdate, r
   FROM(
        SELECT lunchno, title, content, image1, image2, rdate, rownum as r
        FROM (
              SELECT lunchno, title, content, image1, image2, rdate
              FROM lunch
              ORDER BY lunchno
        )
   )
   WHERE r >=1 AND r<=5;



SELECT lunchno, title, content, image1, image2, rdate, r
   FROM(
        SELECT lunchno, title, content, image1, image2, rdate, rownum as r
        FROM (
              SELECT lunchno, title, content, image1, image2, rdate
              FROM lunch
              ORDER BY lunchno
        )
   )
   WHERE r >=6 AND r<=10;
   
   
   
    
        
[과제9] photo 테이블의 레코드를 상위 기준으로 1~ 5번째, 6~10번째
        레코드를 SELECT하는 서브쿼리를 생성하세요.

