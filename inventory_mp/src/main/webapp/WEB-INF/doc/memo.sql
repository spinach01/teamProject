1. memo ���̺� ����

1) ���̺� ����: /WebContent/doc/memo.sql
- NOT NULL   : INSERT�� �ʼ� �Է�, ���� �Ұ���
- DEFAULT 0  : INSERT�� ���� �Է����� ������ 0�� �߰���.
- DEFAULT 'A': INSERT�� ���� �Է����� ������ 'A'�� �߰���.
- NUMBER(6)  : int �迭, -999999 ~ +999999, ������ ����Ʈ ����
- VARCHAR(30): String �迭, ���ڿ� ����, �������� ���ڿ�, ����� ����,
               �ѱ� 15��, �����ڴ� 30��
- DATE       : ��¥


-- �ӽ� �������� ���̺� ����, ���� ������ ���������� ����
DROP TABLE memo;

-- ���̺��� ���� �Ұ��� �ϵ��� ����
DROP TABLE memo PURGE;

-- ���̺� ����
CREATE TABLE memo(
    memono     NUMBER(6)     NOT NULL, -- �Ϸù�ȣ, -999999 ~ +999999
    title      VARCHAR(50)   NOT NULL, -- �޸� ����, �ѱ� 25��
    content    VARCHAR(4000) NOT NULL, -- ���� 
    wdate      DATE          NOT NULL, -- ��� ��¥ 
    viewcnt    NUMBER(5)     DEFAULT 0, -- ��ȸ�� 
    PRIMARY KEY(memono) 
);



2) SEQUENCE (MySQL: AUTO_INCREMENT ������ ���)
- ���ڵ�� �����ϰ� ���еǾ���ϴ� ������ ������
  ������ ���� �����ϱ����� �����͸� ���а����� ����ϴ� ��찡 �ְ�
  Sequence�� �Ϸù�ȣó�� ����Ŭ���� �����Ǵ� ���� ����ϴ� ��쵵 ����.

  START WITH 0: �Ϸù�ȣ 1���� ����
  INCREMENT BY 1: �Ϸù�ȣ�� 1 �� ����
  CACHE 2: �������� ���̺� ���������� ���� Update�� �߻�������
           �̰��� ���̱����� �޸𸮿��� �Ϸù�ȣ�� ������Ű��
           CACHE ����� �̿��� ���� ������ ������ �� ����, 
           �޸𸮿��� �Ϸù�ȣ ����, ó�� �ӵ� ���,
           �⺻���� 20��, INSERT�� ���� �߻��ϸ� �⺻���� ��� ���� 
  
  MySQL: memono   INT NOT NULL AUTO_INCREMENT PRIMARY KEY


-- SEQUENCE ����
DROP SEQUENCE memo_seq;

-- SEQUENCE ����
CREATE SEQUENCE memo_seq
START WITH 0
INCREMENT BY 1
MINVALUE 0
CACHE 2; -- 2����



-- create
-- memo_seq.nextval: �Ϸù�ȣ �ڵ� �����Ͽ� �Ҵ�
--                   (MySQL�� �÷����� ������� ����).
-- sysdate: ���� ��¥�� �ð�(MySQL: now())
INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '������ �޸�1', '������ �޸�1 ����', sysdate);

INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '������ �޸�2', '������ �޸�2 ����', sysdate);

INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '������ �޸�3', '������ �޸�3 ����', sysdate);

INSERT INTO memo
VALUES(memo_seq.nextval, '������ �޸�4', '������ �޸�4 ����', sysdate);



-- ERROR
-- �ѱ� 1�ڴ� ������ 2�� ó����, ���� ������ 50�̳� �Ʒ��� 52����Ʈ������ ERROR
INSERT INTO memo(memono, title, content, wdate)
VALUES(memo_seq.nextval, '�����ٶ󸶹ٻ����īŸ�����ٶ󸶰����ٶ󸶹ٻ����ī', 'TEST', sysdate);


-- select list, ���� �д� �޸� ���� ��� 
-- DESC: Descending, ASC: Ascending
SELECT memono, title, content, wdate, viewcnt
FROM memo 
ORDER BY memono ASC;
------------------------------------------------------------

UPDATE memo
SET title='�����', content='ģ��������'
WHERE memono=13;

DELETE FROM memo
WHERE memono= 20;

-- ��ȸ�� ����
UPDATE memo
SET viewcnt = viewcnt + 1
WHERE memono = 1;