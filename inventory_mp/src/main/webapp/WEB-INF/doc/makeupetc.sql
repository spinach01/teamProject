DROP TABLE mpimport PURGE;
DROP TABLE mpexport PURGE;
DROP TABLE mpreturn PURGE;

CREATE TABLE mpimport(
    importno                          NUMBER(7)    NOT NULL    PRIMARY KEY,
    imdate                            DATE     NOT NULL,
    imcount                           NUMBER(10)     NOT NULL,
    imprice                           NUMBER(10)     NOT NULL,
    imclient                          VARCHAR2(50)     NOT NULL,
    productno                         NUMBER(7)    NULL 
);

1. 등록
INSERT INTO mpimport(importno, imdate, imcount, imprice, imclient, productno)
VALUES((SELECT NVL(MAX(importno), 0)+1 as importno FROM mpimport), 
      sysdate, 50, 25000, '의정부대리점', 1);

2. 목록
SELECT importno, imdate, imcount, imprice, imclient, productno
FROM mpimport;
ORDER BY importno ASC;

SELECT importno, imdate, imcount, imprice, imclient, productno
FROM mpimport
WHERE productno = 1;

DELETE FROM mpimport 
WHERE importno = 7;      
      

-------------------------------------------------------------------------

CREATE TABLE mpexport(
    exportno                          NUMBER(7)    NOT NULL    PRIMARY KEY,
    exdate                            DATE     NOT NULL,
    excount                           NUMBER(10)     NOT NULL,
    exprice                           NUMBER(10)     NULL ,
    excustumer                        VARCHAR2(50)     NOT NULL,
    productno                         NUMBER(7)    NULL 
);


1. 등록
INSERT INTO mpexport(exportno, exdate, excount, exprice, excustumer, productno)
VALUES((SELECT NVL(MAX(exportno), 0)+1 as exportno FROM mpexport), 
      sysdate, 100, 22000, '아모레퍼시픽', 1);

2. 목록
SELECT exportno, exdate, excount, exprice, excustumer, productno
FROM mpexport;
ORDER BY exportno ASC;

SELECT exportno, exdate, excount, exprice, excustumer, productno
FROM mpexport
WHERE productno = 1;

DELETE FROM mpexport 
WHERE exportno = 12;  

----------------------------------------------------------------

CREATE TABLE mpreturn(
    returnno                          NUMBER(7)    NOT NULL    PRIMARY KEY,
    retdate                           DATE     NOT NULL,
    retcount                          NUMBER(10)     NOT NULL,
    productno                         NUMBER(7)    NULL 
);

1. 등록
INSERT INTO mpreturn(returnno, retdate, retcount, productno)
VALUES((SELECT NVL(MAX(returnno), 0)+1 as returnno FROM mpreturn), 
      sysdate, 10, 1);

2. 목록
SELECT returnno, retdate, retcount, productno
FROM mpreturn;
ORDER BY returnno ASC;

SELECT returnno, retdate, retcount, productno
FROM mpreturn
WHERE productno = 1;

DELETE FROM mpreturn 
WHERE returnno = 1;  

