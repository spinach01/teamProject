CREATE TABLE madmin ( -- ������
    id       VARCHAR(10) NOT NULL, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 �����ϴ� �÷� 
    passwd   VARCHAR(20) NOT NULL, -- �н�����, ������ ����
    mname    VARCHAR(20) NOT NULL, -- ����, �ѱ� 10�� ���� ����
    tel      VARCHAR(14)     NULL, -- ��ȭ��ȣ
    email    VARCHAR(50) NOT NULL UNIQUE, -- ���ڿ��� �ּ�, �ߺ� �ȵ�
    zipcode  VARCHAR(7)      NULL, -- �����ȣ, 101-101
    address1 VARCHAR(50)     NULL, -- �ּ� 1
    address2 VARCHAR(50)     NULL, -- �ּ� 2
    position VARCHAR(20) NOT NULL, -- ����
    mdate    DATE        NOT NULL, -- �Ի���    
    fname    VARCHAR(50) DEFAULT 'member.jpg', -- ȸ�� ����
    PRIMARY KEY (id)               -- �ѹ� ��ϵ� id�� �ߺ� �ȵ�
);

DROP TABLE madmin PURGE;

CREATE TABLE employee ( -- ���
    id       VARCHAR(10) NOT NULL, -- ���̵�, �ߺ� �ȵ�, ���ڵ带 �����ϴ� �÷� 
    passwd   VARCHAR(20) NOT NULL, -- �н�����, ������ ����
    mname    VARCHAR(20) NOT NULL, -- ����, �ѱ� 10�� ���� ����
    tel      VARCHAR(14)     NULL, -- ��ȭ��ȣ
    email    VARCHAR(50) NOT NULL UNIQUE, -- ���ڿ��� �ּ�, �ߺ� �ȵ�
    zipcode  VARCHAR(7)      NULL, -- �����ȣ, 101-101
    address1 VARCHAR(50)     NULL, -- �ּ� 1
    address2 VARCHAR(50)     NULL, -- �ּ� 2
    position VARCHAR(20) NOT NULL, -- ����
    mdate    DATE        NOT NULL, -- �Ի���    
    fname    VARCHAR(50) DEFAULT 'member.jpg', -- ȸ�� ����
    mtype    NUMBER(9)   NOT NULL, -- 1:����, 2: �Ϲ� ȸ�� 3:�Ǹ���  
    PRIMARY KEY (id)               -- �ѹ� ��ϵ� id�� �ߺ� �ȵ�
);

CREATE TABLE product ( -- ��ǰ
    productno NUMBER(7)    NOT NULL,  -- ǰ��
    pname     VARCHAR(20)  NOT NULL,  -- ǰ��
    price     NUMBER(10)   NOT NULL,  -- �ǸŰ�
    rdate     DATE         NOT NULL,  -- �԰���
    fname     VARCHAR(50)  DEFAULT 'product.jpg', -- ��ǰ ����
    mcount    NUMBER(10)   NOT NULL,  -- ����
    content   VARCHAR(3000) NOT NULL,  -- ��ǰ ����
    volume    VARCHAR(10)  NOT NULL,  -- �뷮
    mtype     VARCHAR(20)  NOT NULL,  -- �Ǻ� Ÿ��  -- �Ǹ� ȸ��-> �Ǹ���(FK):������� ������
    morder    NUMBER(10)   NOT NULL,  -- �ֹ�����
    etc       VARCHAR(200) NOT NULL,  -- ���  
    PRIMARY KEY (productno)  
);

CREATE TABLE pgroup ( -- ȭ��ǰ �з�
    pgroupno  NUMBER(7)    NOT NULL,  -- �з� ��ȣ -- ��Ų�ɾ�, �μ�, ����~~~~
    pgname    VARCHAR(20)  NOT NULL,  -- �з���
    PRIMARY KEY (pgroupno)  
);

CREATE TABLE qna ( -- Q&A
  qnano   NUMBER(7)     NOT NULL,  -- ������ ��ȣ, -9999999 ~ +9999999
  wname   VARCHAR(20)   NOT NULL,  -- �۾���
  title   VARCHAR(100)  NOT NULL,  -- ����(*)
  content VARCHAR(4000) NOT NULL,  -- �� ����
  passwd  VARCHAR(15)   NOT NULL,  -- ��� ��ȣ
  viewcnt NUMBER(5)     DEFAULT 0, -- ��ȸ��, �⺻�� ���
  wdate   DATE          NOT NULL,  -- ��� ��¥, sysdate
  grpno   NUMBER(7)     DEFAULT 0, -- �θ�� ��ȣ
  indent  NUMBER(2)     DEFAULT 0, -- �亯����
  ansnum  NUMBER(5)     DEFAULT 0, -- �亯 ����
  PRIMARY KEY (qnano) 
);

CREATE TABLE rdata ( -- �ڷ�� -- ���� �ؾ��� -- �����Խ���ó��.... --�α��� �� ����� �ѿ��� �����ϵ���
  rdatano   NUMBER(7)     NOT NULL,  -- �ڷ� ��ȣ, -9999999 ~ +9999999
  title     VARCHAR(100)  NOT NULL,  -- ����(*), �ѱ�50��, ������ 100��
  rdate     DATE          NOT NULL,  -- ��� ��¥, sysdate
  fname     VARCHAR(50)   NULL,      -- ���� ���ϸ�, tulip01.jpg
  fsize     NUMBER(9)     DEFAULT 0 NOT NULL,  -- ���� ���� ������
  content   VARCHAR(4000) NOT NULL,  -- �� ����
  passwd    VARCHAR(15)   NOT NULL,  -- ��� ��ȣ  
  viewcnt   NUMBER(5)     DEFAULT 0, -- ��ȸ��, �⺻�� ���
  grpno     NUMBER(7)     DEFAULT 0, -- �θ�� ��ȣ
  indent    NUMBER(2)     DEFAULT 0, -- �亯����
  ansnum    NUMBER(5)     DEFAULT 0, -- �亯 ����  
  PRIMARY KEY (rdatano) 
);


CREATE TABLE notice ( -- �������� -- ������ ����
  notno   NUMBER(7)     NOT NULL,  -- �ڷ� ��ȣ, -9999999 ~ +9999999
  title     VARCHAR(100)  NOT NULL,  -- ����(*), �ѱ�50��, ������ 100��
  rdate     DATE          NOT NULL,  -- ��� ��¥, sysdate
  fname     VARCHAR(50)   NULL,      -- ���� ���ϸ�, tulip01.jpg
  fsize     NUMBER(9)     DEFAULT 0 NOT NULL,  -- ���� ���� ������
  content   VARCHAR(4000) NOT NULL,  -- �� ����
  viewcnt   NUMBER(5)     DEFAULT 0, -- ��ȸ��, �⺻�� ���
  PRIMARY KEY (notno) 
);

CREATE TABLE import ( -- �԰�
  imno   NUMBER(7)        NOT NULL,   -- �԰�ǰ��ȣ  -9999999 ~ +9999999 - ǰ��(FK)
  idate      DATE          NOT NULL,  -- �԰���
  icount     NUMBER(10)    NOT NULL,  -- �԰�
  iprice     NUMBER(10)   NOT NULL,   -- ���԰�   -- ���(FK)
  PRIMARY KEY (imno) 
);

CREATE TABLE export ( -- ���
  exno       NUMBER(7)        NOT NULL,  -- �԰�ǰ��ȣ  -9999999 ~ +9999999 - ǰ��(FK)
  edate      DATE          NOT NULL,     -- �԰���
  ecount     NUMBER(10)    NOT NULL,     -- �԰�
  factory    VARCHAR(50)   NULL,         -- �����   -- ���(FK), ǰ���� ���ݰ�FK
  PRIMARY KEY (exno) 
);


CREATE TABLE ereturn ( -- ��ǰ
  erno       NUMBER(7)      NOT NULL,    -- �԰�ǰ��ȣ  -9999999 ~ +9999999 - ǰ��(FK)
  erdate     DATE           NOT NULL,    -- �԰���
  ercount     NUMBER(10)    NOT NULL,    -- �԰�   -- ���(FK)
  factory    VARCHAR(50)   NULL,         -- �����
  PRIMARY KEY (erno) 
);


CREATE TABLE product ( -- �Ǹ���  -- ���߿� ����
    salerno   NUMBER(7)    NOT NULL,  -- �Ǹ��� ��ȣ
    sname     VARCHAR(20)  NOT NULL,  -- ǰ��
    price     NUMBER(10)   NOT NULL,  -- �ܰ�
    rdate     DATE         NOT NULL,  -- �԰���
    fname     VARCHAR(50)  DEFAULT 'product.jpg', -- ��ǰ ����
    count     NUMBER(10)   NOT NULL,  -- ����
    content   VARCHAR(300) NOT NULL,  -- ��ǰ ����
    volume    VARCHAR(10)  NOT NULL,  -- �뷮
    mtype     VARCHAR(10)  NOT NULL,  -- �Ǻ� Ÿ��  -- �Ǹ� ȸ��-> �Ǹ���(FK)
    morder    NUMBER(10)   NOT NULL,  -- �ֹ�����
    etc       VARCHAR(200) NOT NULL,  -- ��� 
    PRIMARY KEY (productno)  
);


--�Ǹ� ��ü ������ ���¾�ü ��ũ�� �ص� ��,