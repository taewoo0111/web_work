create table test_users(
	id varchar(20) primary key,
	password varchar(20) not null,
	username varchar(20) unique
);

create table test_posts(
	num number primary key,
	writer varchar2(20) not null,
	title varchar2(20) not null,
	content CLOB,
	viewcount number default 0,
	createdat date default sysdate,
	updatedat date default sysdate
);
--게시판 게시글의 고유번호-- 
create sequence test_posts_seq;

create table test_readed(
	num number references test_posts(num),
	session_id varchar2(20)
);