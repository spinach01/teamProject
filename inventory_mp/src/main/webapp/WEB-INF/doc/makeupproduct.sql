DROP TABLE mpgroup PURGE;
DROP TABLE mpproduct PURGE;

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


1. 등록
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '스킨', 15000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '스킨', 15000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');
      
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '스킨', 15000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 10000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '건성', '검토', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '복합성', '검토', 1, 'user1');        

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 20000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성성', '검토', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '복합성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '에센스', 10000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '에센스', 158000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '복합성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '에센스', 10000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '건성', '검토', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '건성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '건성', '검토', 1, 'user1');              
      
 INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '복합성', '검토', 1, 'user1');              
     
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '복합성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '로션', 18000, sysdate, 'poster.jpg', 50, '지성피부에 좋다', '100', '지성', '검토', 1, 'user1');              
      
2. 목록
SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id
FROM mpproduct
WHERE mpgroupno = 1;

DELETE FROM mpproduct 
WHERE productno = 1;

3.화장품명의 추출
SELECT mpname
FROM mpproduct;

 MPNAME
 ------
 스킨
 스킨
 스킨
 스킨
 로션

 
SELECT DISTINCT mpname, ftype, price  
FROM mpproduct;

SELECT DISTINCT ftype  
FROM mpproduct;


 MPNAME
 ------
 스킨
 로션
 
 
SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id
FROM mpproduct
WHERE mpname LIKE '%스킨%'
ORDER BY productno ASC;
 
 
 **** 다음 조회는 연봉이 20000 미만인 직원이 있는 부서를 모두 찾습니다.
SELECT DISTINCT  e.dept  FROM EmpBean e where e.salary < 20000
