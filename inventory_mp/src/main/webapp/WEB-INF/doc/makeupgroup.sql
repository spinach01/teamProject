DROP TABLE mpgroup PURGE;
DROP TABLE mpproduct PURGE;

CREATE TABLE mpgroup(
    mpgroupno                         NUMBER(7)    NOT NULL    PRIMARY KEY,
    mpgrname                          VARCHAR2(50)     NOT NULL
);

1. ���(�߰�)
INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '���ʶ���');

INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '��������');

INSERT INTO mpgroup(mpgroupno, mpgrname)
VALUES((SELECT NVL(MAX(mpgroupno), 0)+1 as mpgroupno FROM mpgroup), '��������');

SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 ���ʶ���
         2 ��������

         
2. ���
SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 ���ʶ���
         2 ��������       
         
3. ��ȸ
SELECT mpgroupno, mpgrname
FROM mpgroup
WHERE mpgroupno = 1;

 MPGROUPNO MPGRNAME
 --------- --------
         1 ���ʶ���

4. ����
UPDATE mpgroup
SET mpgrname='�������ʶ���'
WHERE mpgroupno = 1;          
         
 MPGROUPNO MPGRNAME
 --------- --------
         1 �������ʶ���
         
5. ����
SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 �������ʶ���
         2 ��������
         3 ���ʶ���

DELETE FROM mpgroup 
WHERE mpgroupno = 3;

SELECT mpgroupno, mpgrname
FROM mpgroup
ORDER BY mpgroupno ASC;

 MPGROUPNO MPGRNAME
 --------- --------
         1 �������ʶ���
         2 ��������
         

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








            
            