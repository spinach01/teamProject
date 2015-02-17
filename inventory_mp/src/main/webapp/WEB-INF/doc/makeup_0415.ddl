CREATE TABLE mpgroup(
		mpgroupno                     		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		mpgrname                      		VARCHAR2(20)		 NOT NULL
);

DROP TABLE employee PURGE;
CREATE TABLE employee(
		id                            		VARCHAR2(10)		 NOT NULL		 PRIMARY KEY,
		passwd                        		VARCHAR2(20)		 NOT NULL,
		mname                         		VARCHAR2(20)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NULL ,
		email                         		VARCHAR2(50)		 NOT NULL,
		zipcode                       		VARCHAR2(7)		   NULL ,
		address1                      		VARCHAR2(50)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		position                      		VARCHAR2(20)		 NULL,
		mdate                         		DATE		 NOT     NULL,
		fname                         		VARCHAR2(50)		 DEFAULT 'member.jpg'		 NULL ,
		mtype                         		VARCHAR2(20)		 NULL,
  CONSTRAINT SYS_C0060009 UNIQUE (EMAIL)
);


CREATE TABLE mpproduct(
		productno                     		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		mpname                        		VARCHAR2(20)		 NOT NULL,
		price                         		NUMBER(10)		 NOT NULL,
		rdate                         		DATE		 NOT NULL,
		fname                         		VARCHAR2(50)		 NULL ,
		mcount                        		NUMBER(10)		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		volume                        		VARCHAR2(10)		 NOT NULL,
		ftype                         		VARCHAR2(20)		 NOT NULL,
		etc                           		VARCHAR2(200)		 NULL ,
		mpgroupno                     		NUMBER(7)		 NULL ,
		id                            		VARCHAR2(10)		 NULL 
);


CREATE TABLE mpadmin(
		id                            		VARCHAR2(10)		 NOT NULL		 PRIMARY KEY,
		passwd                        		VARCHAR2(20)		 NOT NULL,
		mname                         		VARCHAR2(20)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NULL ,
		email                         		VARCHAR2(50)		 NOT NULL,
		zipcode                       		VARCHAR2(7)		 NULL ,
		address1                      		VARCHAR2(50)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		position                      		VARCHAR2(20)		 NOT NULL,
		mpdate                        		DATE		 NOT NULL,
		fname                         		VARCHAR2(50)		 DEFAULT 'member.jpg'		 NULL ,
  CONSTRAINT SYS_C0060077 UNIQUE (EMAIL)
);


CREATE TABLE clientbbs(
		qnano                         		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		wname                         		VARCHAR2(20)		 NOT NULL,
		title                         		VARCHAR2(100)		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		passwd                        		VARCHAR2(15)		 NOT NULL,
		viewcnt                       		NUMBER(5)		 NULL ,
		wdate                         		DATE		 NOT NULL,
		grpno                         		NUMBER(7)		 NULL ,
		indent                        		NUMBER(2)		 NULL ,
		ansnum                        		NUMBER(5)		 NULL ,
		id                            		VARCHAR2(10)		 NULL 
);


CREATE TABLE clientdata(
		cdatano                       		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		wname                         		VARCHAR2(20)		 NOT NULL,
		title                         		VARCHAR2(100)		 NOT NULL,
		fname                         		VARCHAR2(50)		 DEFAULT 'data.jpg'		 NULL ,
		filesize                      		NUMBER(9)		 DEFAULT 0		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		passwd                        		VARCHAR2(15)		 NOT NULL,
		mviewcnt                      		NUMBER(5)		 NULL ,
		rdate                         		DATE		 NOT NULL,
		grpno                         		NUMBER(7)		 NULL ,
		indent                        		NUMBER(2)		 NULL ,
		ansnum                        		NUMBER(5)		 NULL ,
		id                            		VARCHAR2(10)		 NULL 
);


CREATE TABLE mpnotice(
		noticeno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(100)		 NOT NULL,
		wdate                         		DATE		 NOT NULL,
		fname                         		VARCHAR2(50)		 DEFAULT 'notice.jpg'		 NULL ,
		content                       		VARCHAR2(4000)		 NOT NULL,
		mviewcnt                      		NUMBER(5)		 NULL 
);


CREATE TABLE mpimport(
		importno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		imdate                        		DATE		 NOT NULL,
		imcount                       		NUMBER(10)		 NOT NULL,
		imprice                       		NUMBER(10)		 NOT NULL,
		imclient                      		VARCHAR2(50)		 NOT NULL,
		productno                     		NUMBER(7)		 NULL 
);


CREATE TABLE mpexport(
		exportno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		exdate                        		DATE		 NOT NULL,
		excount                       		NUMBER(10)		 NOT NULL,
		exprice                       		NUMBER(10)		 NULL ,
		excustumer                    		VARCHAR2(50)		 NOT NULL,
		productno                     		NUMBER(7)		 NULL 
);


CREATE TABLE mpreturn(
		returnno                      		NUMBER(7)		 NOT NULL		 PRIMARY KEY,
		retdate                       		DATE		 NOT NULL,
		retcount                      		NUMBER(10)		 NOT NULL,
		productno                     		NUMBER(7)		 NULL 
);


