-- 가입된 회원정보를 저장할 테이블 --
create table users(
 	num number primary key,
 	username varchar2(100) unique,
 	password varchar2(100) not null,
 	email varchar2(100) unique,
 	profileimage varchar2(100),
 	role varchar2(10) default 'user' check (role in('ADMIN', 'STAFF')),
 	createdat date default sysdate,
 	updatedat date default sysdate
);

-- 회원번호를 얻어낼 시퀀스 --
create sequence users_seq;

CREATE TABLE posts(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL,
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER DEFAULT 0,
	createdAt DATE DEFAULT SYSDATE,
	updatedAt DATE DEFAULT SYSDATE
);

CREATE SEQUENCE posts_seq;

-- 어떤 세션에서 몇번글을 읽었는지 정보를 저장할 테이블
CREATE TABLE readed_data(
	num NUMBER REFERENCES posts(num),
	session_id VARCHAR2(50)
);

create table comments(
	num number primary key,
	writer varchar2(100) not null,
	content varchar2(200) not null,
	targetWriter varchar2(100) not null,
	postNum number not null,
	parentNum number not null,
	deleted char(3) default 'no',
	createdAt date
);

create sequence comments_seq;