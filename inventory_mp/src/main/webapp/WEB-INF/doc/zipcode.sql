DROP TABLE zipcode CASCADE CONSTRAINTS;

CREATE TABLE zipcode (
    zipcode CHAR(7)      NOT NULL, -- �����ȣ
    sido    VARCHAR(10)  NOT NULL, -- ��, ��
    gugun   VARCHAR(20)  NOT NULL, -- ��, ��
    dongli  VARCHAR(100) NOT NULL, -- ��, ��
    etc     VARCHAR(30)      NULL  -- ���� �ּ�
);


insert into zipcode values('405-807','��õ','������','����1��','272��293����');
insert into zipcode values('405-807','��õ','������','����1��','308��343');
insert into zipcode values('405-809','��õ','������','����1��','345��354');
insert into zipcode values('405-809','��õ','������','����1��','399��525');
insert into zipcode values('405-811','��õ','������','����1��','531��535');
insert into zipcode values('405-811','��õ','������','����1��','537��570');
insert into zipcode values('405-814','��õ','������','����1��','896��908');
insert into zipcode values('405-231','��õ','������','����1��',' ');
insert into zipcode values('405-705','��õ','������','����1�� �ص�����Ʈ',' ');
insert into zipcode values('405-706','��õ','������','����1�� ��ȣ����Ʈ',' ');
insert into zipcode values('405-708','��õ','������','����1�� �������Ʈ',' ');
insert into zipcode values('405-709','��õ','������','����1�� �۸�����Ʈ',' ');
insert into zipcode values('405-710','��õ','������','����1�� ��������Ʈ',' ');
insert into zipcode values('405-713','��õ','������','����1�� ��ȭ����Ʈ',' ');
insert into zipcode values('405-714','��õ','������','����1�� ��������Ʈ',' ');
insert into zipcode values('405-715','��õ','������','����1�� ��������Ʈ',' ');
insert into zipcode values('405-716','��õ','������','����1�� �������Ʈ',' ');
insert into zipcode values('405-802','��õ','������','����2��','126��169����');
insert into zipcode values('405-803','��õ','������','����2��','173��210');
insert into zipcode values('405-808','��õ','������','����2��','294��307');
insert into zipcode values('405-808','��õ','������','����2��','344');
insert into zipcode values('405-232','��õ','������','����2��',' ');
insert into zipcode values('405-717','��õ','������','����2�� �����ְ��Ǽ�','(53��74��)');
insert into zipcode values('405-751','��õ','������','����2�� �����ְ�����Ʈ','(1��52��)');
insert into zipcode values('405-718','��õ','������','����2�� ��ž���Ʈ',' ');
insert into zipcode values('405-800','��õ','������','����3��','1��71����');
insert into zipcode values('405-801','��õ','������','����3��','72��125');
insert into zipcode values('405-801','��õ','������','����3��','170��172');
insert into zipcode values('405-805','��õ','������','����3��','224��225');
insert into zipcode values('405-812','��õ','������','����3��','768��775');
insert into zipcode values('405-815','��õ','������','����3��','909��921');
insert into zipcode values('405-233','��õ','������','����3��',' ');
insert into zipcode values('405-701','��õ','������','����3�� ��õ�����',' ');
insert into zipcode values('405-719','��õ','������','����3�� ��������Ʈ',' ');
insert into zipcode values('405-720','��õ','������','����3�� ���Ѿ���Ʈ',' ');
insert into zipcode values('405-723','��õ','������','����3�� ����ö����',' ');
insert into zipcode values('405-724','��õ','������','����3�� ��ȭ����Ʈ',' ');
insert into zipcode values('405-725','��õ','������','����3�� �Ҵ�����Ʈ',' ');
insert into zipcode values('405-804','��õ','������','����4��','211��223����');
insert into zipcode values('405-806','��õ','������','����4��','226��271');
insert into zipcode values('405-806','��õ','������','����4��','355��398');
insert into zipcode values('405-810','��õ','������','����4��','526��530');
insert into zipcode values('405-810','��õ','������','����4��','536');
insert into zipcode values('405-810','��õ','������','����4��','571��767');
insert into zipcode values('405-813','��õ','������','����4��','776��895');
insert into zipcode values('405-234','��õ','������','����4��',' ');
insert into zipcode values('405-752','��õ','������','����4�� �켺����Ʈ',' ');
insert into zipcode values('405-726','��õ','������','����4�� �־��ְ�����Ʈ',' ');
insert into zipcode values('405-230','��õ','������','������',' ');


SELECT COUNT(*) as cnt 
FROM zipcode 
ORDER BY zipcode;


SELECT zipcode, sido, gugun, dongli, etc
FROM zipcode
WHERE (dongli LIKE '%����4��%')
ORDER BY sido, gugun, dongli;


    
 
[02] SubQuery

��. ����: SQL�ȿ� SQL�� ����(��ø)�Ǿ� �ִ� ����


��. Ư¡:
    1) ���ڵ尡 ���� ��� ���ڵ带 �и��ؼ� DB�κ��� �����ؾ�
       �ϴµ� �̶� ����ϴ� ������ Subquery �Դϴ�.

    2) Subquery�� ������� �ʰ� ���ڵ� ����� ������ ����
       100~200�� ������ �����ؼ� �о���� ������ ������
       ���� �������� ����. 
       ��) SQL Explorer Tool

    3) ���� ������ subquery�� ���� ����ǰ� �ٱ�����
       ���������� ����˴ϴ�. 
 
    4) �����ͺ��̽� ������ ���� ���ϴ� �߻��� �� ������
       ��Ʈ��ũ Ʈ������ ���ҵǸ� �ڹ� �ڵ带 ���� �� �ִ� ������
       �ֽ��ϴ�. ������ �޸𸮸� ������ �� �ִ�.

    5) �ʹ� ���� �������� ����� DB ������ �ӵ��� ������ ������
       ���ϰ� �ɸ��� �ʴ� ������ ������ ����ؾ� �մϴ�.

    6) SubQuery�� View�� ����� �ȴ�. 
 
    7) ���������� ����� ������� MySQL-LIMIT, MS-SQL-TOP�� ����.



1. SubQuery �ǽ�
   SELECT COUNT(*) FROM zipcode;

   COUNT(*)
   --------
         49

       
>>>>> /WebContent/doc/subquery.sql

1) ��ü ���ڵ� ���
   SELECT zipcode, sido, gugun, dongli, etc
   FROM zipcode
   ORDER BY sido, gugun, dongli, etc;
   
   
   
2) SELECT ����� ���� 5���� �����ȣ�� ���
   - rownum ���� �÷��� �̿��մϴ�.
   - subquery�� ����ؾ� �մϴ�.   
   


3) rownum ���� �÷��� �̿�: SELECT�� �Ϸù�ȣ ���� 
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM zipcode;



4) ���Ľ��� rownum ���� �÷��� �̿�
   - ���ڵ忡 rownum �÷��� ���ǰ� �� �� �����̵Ǿ� 
     rownum �÷��� ���� ���������� ��µ��� �ʾҽ��ϴ�.
     
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM zipcode
   ORDER BY sido, gugun, dongli, etc;



5) ������ ���� ���� ����� �޾� rownum �÷��� ����մϴ�.

   - �޸𸮻󿡼��� SELECT ����� ���̺�� ����������
     �÷��� �״�� �޾� rownum�� �Բ� ���
     
   SELECT zipcode, sido, gugun, dongli, etc, rownum
   FROM (
         SELECT zipcode, sido, gugun, dongli, etc
         FROM zipcode
         ORDER BY sido, gugun, dongli, etc
   );



6) Ư�� ���ڵ常 ����, ���� 5���� ����
   - WHERE ���� rownum�� ���߿� ���������� WHERE������ rownum��
     ����� �ν��� ����.


   SELECT zipcode, sido, gugun, dongli, etc, rownum as r
   FROM (
         SELECT zipcode, sido, gugun, dongli, etc
         FROM zipcode
         ORDER BY sido, gugun, dongli, etc
   )
   WHERE r <= 5;



7) Ư�� ���ڵ常 ����, ���� 5���� ����

   - ���������� 3������ �մϴ�.rownum�� r�÷��� �޽��ϴ�.
   
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
 405-231 ��õ   ������   ����1��             1
 405-807 ��õ   ������   ����1��   272��293���� 2
 405-807 ��õ   ������   ����1��   308��343   3
 405-809 ��õ   ������   ����1��   345��354   4
 405-809 ��õ   ������   ����1��   399��525   5
 
 
 
8) Ư�� ���ڵ常 ����, ���� 6 ~ 10���� ����

   - ���������� 3������ �մϴ�.rownum�� r�÷��� �޽��ϴ�.
   
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
 405-809 ��õ   ������   ����1��       399��525  5
 405-811 ��õ   ������   ����1��       531��535  6
 405-811 ��õ   ������   ����1��       537��570  7
 405-814 ��õ   ������   ����1��       896��908  8
 405-705 ��õ   ������   ����1�� �ص�����Ʈ          9
 405-706 ��õ   ������   ����1�� ��ȣ����Ʈ         10
 
 
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
    





[����1] �������� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE ntc(
    ntcno NUMBER(7)     NOT NULL, -- �Ϸù�ȣ
    title    VARCHAR(100)  NOT NULL, -- ����
    content  VARCHAR(2000) NOT NULL, -- ����
    writer   VARCHAR(20)   NOT NULL, -- �ۼ���
    passwd   VARCHAR(15)   NOT NULL, -- �н�����
    ndate    DATE          NOT NULL, -- �ۼ���
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



[����2] ȸ�� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE memberSub (
    id       VARCHAR(10) NOT NULL, -- ���̵�, �ߺ� �ȵ�
    passwd   VARCHAR(10) NOT NULL, -- �н�����
    mname    VARCHAR(20) NOT NULL, -- ���� 
    zipcode  VARCHAR(7)      NULL, -- �����ȣ, 101-101
    memlevel CHAR(2)     NOT NULL, -- ȸ�� ���, AA, BA, CA, DA, EA, FA
    memdate  DATE        NOT NULL, -- ȸ�� ������
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


[����3] ���� ���� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

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



[����4] ���� �׸� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE survey(
    surveyno      NUMBER(5)    NOT NULL, -- �׸� �Ϸ� ��ȣ
    surveytitleno NUMBER(5)    NOT NULL, -- ���� ���� ��ȣ
    content       VARCHAR(100) DEFAULT 0 NOT NULL, -- ����
    cnt           NUMBER(5)    DEFAULT 0 NOT NULL, -- ���� ��
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
   



[����5] ���� ���� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE surveyEtc(
    surveyetcno   NUMBER(5)    NOT NULL, -- �׸� �Ϸ� ��ȣ
    surveytitleno NUMBER(5)    NOT NULL, -- ���� ���� ��ȣ
    content       VARCHAR(200) NOT NULL, -- ����
    passwd        VARCHAR(10)  NOT NULL, -- ���� ��
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
   
   
   
   
   

[����6] ���� ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE guestbook(
    guestbookcno  NUMBER(5)    NOT NULL, -- ���� �Ϸ� ��ȣ
    content       VARCHAR(500) NOT NULL, -- ����
    passwd        VARCHAR(20)  NOT NULL, -- �н�����
    rdate         DATE         NOT NULL, -- �����  
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


    
[����7] �賬�� ���� �ý��� ���̺��� ���ڵ带 ���� ��������
        1~ 5��°, 6~10��° ���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE fishing(
    fishingno     NUMBER(5)    NOT NULL, -- �賬�� ���� �Ϸ� ��ȣ
    fishcount     NUMBER(5)    NOT NULL, -- �賬�� �ο�                                   
    fishtime      NUMBER(5)    NOT NULL, -- �賬�� �ð�
    rdate         DATE         NOT NULL, -- ���� ��¥
    price         NUMBER(7)    NOT NULL, -- �����  
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
   
   
   
   

[����8] ���ö� �ֹ� �ý��� ���̺��� ���ڵ带 ���� ��������
        1~ 5��°, 6~10��° ���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

CREATE TABLE lunch(
    lunchno  NUMBER(5)    NOT NULL, -- ���� �Ϸ� ��ȣ
    title    VARCHAR(30)  NOT NULL, -- ���ö� ����
    content  VARCHAR(500) NOT NULL, -- ���ö� �� ����
    image1   VARCHAR(30)  NOT NULL, -- ���ö� �̹���1
    image2   VARCHAR(30)  NOT NULL, -- ���ö� �̹���2
    rdate    DATE         NOT NULL, -- �����  
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
   
   
   
    
        
[����9] photo ���̺��� ���ڵ带 ���� �������� 1~ 5��°, 6~10��°
        ���ڵ带 SELECT�ϴ� ���������� �����ϼ���.

