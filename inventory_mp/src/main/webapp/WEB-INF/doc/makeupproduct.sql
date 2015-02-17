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


1. ���
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '��Ų', 15000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '��Ų', 15000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');
      
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '��Ų', 15000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 10000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '�Ǽ�', '����', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '���ռ�', '����', 1, 'user1');        

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 20000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '������', '����', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '���ռ�', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '������', 10000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '������', 158000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '���ռ�', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '������', 10000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '�Ǽ�', '����', 1, 'user1');      

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '�Ǽ�', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '�Ǽ�', '����', 1, 'user1');              
      
 INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '���ռ�', '����', 1, 'user1');              
     
INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '���ռ�', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');              

INSERT INTO mpproduct(productno, 
      mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id)
VALUES((SELECT NVL(MAX(productno), 0)+1 as productno FROM mpproduct), 
      '�μ�', 18000, sysdate, 'poster.jpg', 50, '�����Ǻο� ����', '100', '����', '����', 1, 'user1');              
      
2. ���
SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id
FROM mpproduct
WHERE mpgroupno = 1;

DELETE FROM mpproduct 
WHERE productno = 1;

3.ȭ��ǰ���� ����
SELECT mpname
FROM mpproduct;

 MPNAME
 ------
 ��Ų
 ��Ų
 ��Ų
 ��Ų
 �μ�

 
SELECT DISTINCT mpname, ftype, price  
FROM mpproduct;

SELECT DISTINCT ftype  
FROM mpproduct;


 MPNAME
 ------
 ��Ų
 �μ�
 
 
SELECT productno, mpname, price, rdate, fname, mcount, content, volume, ftype, etc, mpgroupno, id
FROM mpproduct
WHERE mpname LIKE '%��Ų%'
ORDER BY productno ASC;
 
 
 **** ���� ��ȸ�� ������ 20000 �̸��� ������ �ִ� �μ��� ��� ã���ϴ�.
SELECT DISTINCT  e.dept  FROM EmpBean e where e.salary < 20000
