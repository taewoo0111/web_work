-- 가입된 회원정보를 저장할 테이블 --
create table users(
 	num number primary key,
 	username varchar2(100) unique,
 	password varchar2(100) not null,
 	email varchar2(100) unique,
 	profileimage varchar2(100),
 	role varchar2(10) default 'user' check (role in('ADMIN', 'STAFF', 'ADMIN')),
 	createdat date default sysdate,
 	updatedat date default sysdate
);

-- 회원번호를 얻어낼 시퀀스 --
create sequence users_seq;