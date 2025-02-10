create table test_using_app(
	comnum number constraint test_using_app_comnum_uniq unique,
	comname varchar2(50) constraint test_using_app_comname_uniq unique,
	comid number constraint test_using_app_comid_pk primary key,
	createdat date default sysdate
);

create table test_com1_app(
	storenum number constraint test_com1_storenum_pk primary key,
	storecall varchar2(15) constraint test_com1_app_storecall_nn not null
);

create table test_com1_ceo(
	comid number constraint test_com1_ceo_comid_fk references test_using_app(comid),
	empno number constraint test_com1_ceo_empno_pk primary key,
	ename varchar2(20) constraint test_com1_ceo_ename_nn not null,
	role varchar2(10) default 'CEO',
	ecall varchar2(15) constraint test_com1_ceo_ecall_uniq unique,
	epwd varchar2(20) constraint test_com1_ceo_epwd_nn not null
);

create table test_com1_emp(
	comid number constraint test_com1_emp_comid_fk references test_using_app(comid),
	storenum number constraint test_com1_emp_storenum_fk references test_com1_app(storenum),
	empno number constraint test_com1_emp_empno_pk primary key,
	ename varchar2(20) constraint test_com1_emp_ename_nn not null,
	role varchar2(10) check (role in('ADMIN', 'STAFF')),
	ecall varchar2(15) constraint test_com1_emp_ecall_uniq unique,
	epwd varchar2(20) constraint test_com1_emp_epwd_nn not null,
	sal number,
	hsal number,
	worktime number,
	email varchar2(100) constraint test_com1_emp_email_uniq unique,
	hiredate date default sysdate,
	contract CLOB
);

create table test_com1_wait(
	comid number constraint test_com1_wait_comid_fk references test_using_app(comid),
	storenum number constraint test_com1_wait_storenum_fk references test_com1_app(storenum),
	empno number constraint test_com1_wait_empno_pk primary key,
	ename varchar2(20) constraint test_com1_wait_ename_nn not null,
	role varchar2(10) check (role in('ADMIN', 'STAFF')),
	ecall varchar2(15) constraint test_com1_wait_ecall_uniq unique,
	epwd varchar2(20) constraint test_com1_wait_epwd_nn not null,
	sal number,
	hsal number,
	worktime number,
	email varchar2(100) constraint test_com1_wait_email_uniq unique,
	hiredate date default sysdate,
	contract CLOB
);

create sequence test_comnum_seq;

create sequence test_comid_seq;

create sequence test_storenum_seq;

create sequence test_empno_seq;



