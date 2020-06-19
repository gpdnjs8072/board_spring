--회원 테이블
create table member(
	mem_id varchar2(30) primary key,
	mem_pwd varchar2(100) not null,
	mem_email varchar2(100) not null,
	mem_name varchar2(30) not null,
	mem_stateCode varchar2(30) default '101',
	mem_authCode varchar2(30) default '001',
	mem_regdate date default sysdate,
	mem_loginFailCount number default '0'
);

--member 테이블의 제약조건 foreign key
alter table member
add constraint member_mem_stateCode foreign key(mem_stateCode)
references codeDetail(co_code);

--member 테이블의 제약조건 foreign key
alter table member
add constraint member_mem_authCode foreign key(mem_authCode)
references codeDetail(co_code);

--코드 그룹 테이블
create table codeGroup(
	cg_id varchar2(30) primary key,
	cg_name varchar2(30) not null
);

insert into codeGroup (cg_id,cg_name) values('Mem_A','회원 권한 코드');
insert into codeGroup (cg_id,cg_name) values('Mem_S','회원 상태 코드');
insert into codeGroup (cg_id,cg_name) values('Board_T','게시판 종류 코드');

--상세 코드 테이블
create table codeDetail(
	co_code varchar2(30) primary key,
	cg_id varchar2(30),
	co_name varchar2(30) not null,
	co_engName varchar2(30) not null
);

alter table codeDetail
add constraint codeDetail_cg_id foreign key(cg_id)
references codeGroup(cg_id);

--기본입력값
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('001','Mem_A','일반','basic');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('002','Mem_A','회원관리자','memberAdmin');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('003','Mem_A','게시판관리자','boardAdmin');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('101','Mem_S','정상','normal');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('102','Mem_S','정지','stop');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('103','Mem_S','탈퇴','withdraw');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('201','Board_T','공지사항','notice');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('202','Board_T','Q&A','qna');
insert into codeDetail (co_code,cg_id,co_name,co_engName) values('203','Board_T','자유게시판','free');


--게시판 테이블
create table board(
	board_num number primary key,
	board_title varchar2(50) not null,
	board_writer varchar2(30) not null,
	board_content CLOB,
	board_typeCode varchar2(30) not null,
	board_viewCount number default '0',
	board_time date default sysdate,
	file_saveName varchar2(100) is null
);

--게시판에 칼럼추가
alter table board add(file_saveName varchar2(100) ;

--board_num 의 seq
create sequence board_num_seq;

--board테이블의 제약조건 foreign key
alter table board
add constraint board_board_typeCode foreign key(board_typeCode)
references codeDetail(co_code);


--파일 테이블
create table board_file(
	file_num number primary key,
	board_num number,
	file_writer varchar2(30) not null,
	file_oriName varchar2(100) not null,
	file_saveName varchar2(100) not null,
	file_size number not null,
	file_time date default sysdate
);

--file_num 의 seq
create sequence board_file_seq;

--file 테이블의 제약조건 foreign key
alter table board_file
add constraint file_board_num foreign key(board_num)
references board(board_num);