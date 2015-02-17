DROP TABLE mpgroup PURGE;
DROP TABLE mpproduct PURGE;

CREATE TABLE mpgroup(
    mpgroupno                         NUMBER(7)    NOT NULL    PRIMARY KEY,
    mpgrname                          VARCHAR2(50)     NOT NULL
);

1. 등록(추가)
INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '기초라인');

INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '색조라인');

INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '남성라인');

SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 기초라인
         2 색조라인

         
2. 목록
SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 기초라인
         2 색조라인       
         
3. 조회
SELECT mpgroupno, mpgrname
FROM mpgroup
WHERE mpgroupno = 1;

 MPGROUPNO MPGRNAME
 --------- --------
         1 기초라인

4. 수정
UPDATE mpgroup
SET mpgrname='여성기초라인'
WHERE mpgroupno = 1;          
         
 MPGROUPNO MPGRNAME
 --------- --------
         1 여성기초라인
         
5. 삭제
SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 여성기초라인
         2 색조라인
         3 기초라인

DELETE FROM mpgroup 
WHERE mpgroupno = 3;

SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 여성기초라인
         2 색조라인
         

----------------------------------------------------------------------
CREATE TABLE mpproduct(
    productno                         NUMBER(7)    NOT NULL    PRIMARY KEY,
    mpname                            VARCHAR2(20)     NOT NULL,
    price                             NUMBER(10)     NOT NULL,
    rdate                             DATE     NOT NULL,
    fname                             VARCHAR2(50)     NULL ,
    mcount                            NUMBER(10)     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    volume                            VARCHAR2(10)     NOT NULL,
    ftype                             VARCHAR2(20)     NOT NULL,
    etc                               VARCHAR2(200)    NULL ,
    mpgroupno                         NUMBER(7)    NULL ,
    id                                VARCHAR2(10)     NULL 
);








            
            