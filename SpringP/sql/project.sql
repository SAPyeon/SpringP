create database project;
use project;

-- drop table member;
create table member(
id varchar(100) primary key, -- 아이디 
password varchar(100), -- 비밀번호 
name varchar(100) not null unique key, -- 이름
phone varchar(20) not null, 
point int default 0, -- 포인트 
authority boolean default false -- 관리자여부 
);

insert into member(id,password,name,phone,authority)
values('asdf1234','asdf1234','admin','11111',true);

select * from member;


create table authorities(
	id varchar(50) not null,
    authority varchar (50) not null
);

select * from authorities;

insert into authorities(id, authority)
values('aaa','ROLE_USER');

desc stockInfo;
create table StockInfo(
	srtnCd varchar(50) primary key, -- 종목코드
    itmsNm varchar(50),  -- 종목명
	mrktCtg varchar(10),-- 시장구분
	clpr varchar(50), -- 종가
	fltRt varchar(20), -- 등락률
	mkp varchar(50), -- 시가
	basDt varchar(10), -- 기준일자
	hipr varchar(50), -- 고가
	lopr varchar(50), -- 저가
	trqu varchar(50), -- 거래량
	mrktTotAmt varchar(50)-- 시가총액
			
);

select * from stockInfo;
select count(*) from stockinfo;
show table status where name='stockInfo'; -- InnoDB(데이터엔진) 확인
select *
from (
	select @rownum:=@rownum+1 rownum, s.*
	from stockInfo s,(select @rownum:=0) as tmp
    where itmsNm like '%삼%'
    ) as searchlist;

 -- drop table companyInfo;
create table companyInfo(
StaticCode varchar(30) primary key, -- 표준코드
code varchar(20) , -- 단축코드
nameKor varchar(50),-- 한글종목명
itmsNm varchar(50),-- 한글 종목약명
nameEng varchar(100),-- 영문종목명
date varchar(20),-- 상장일
mrktCtg varchar(20), -- 시장구분
stockPart varchar(20),-- 증권구분
emp varchar(10),-- 소속부
stockCat varchar(30),-- 주식종류
price varchar(100), -- 액면가
mrktTotAmt varchar(100),-- 상장주식수
regdate varchar(20) -- 기준일자
);    

select * from companyInfo ; 
select count(*) from companyInfo;   
desc companyInfo;

drop table board;
create table board(
bno int auto_increment primary key, -- 게시판번호
title varchar(1000) not null, -- 글제목
content varchar(10000), -- 게시판 내용 
name varchar(100), -- 작성자 
cnt int default 0, -- 조회수 
regdate datetime default now() -- 작성일
);
desc board;
select * from board;
desc board;

drop table board_reply;

create table board_reply(
rno int auto_increment primary key, -- 리뷰번호
reply varchar(10000), -- 리뷰글
name varchar(100) not null, -- 리뷰작성자
declaration int default 0, -- 신고
regdate datetime default now(), -- 작성일
bno int 
);

desc board_reply;

ALTER TABLE board_reply ADD CONSTRAINT board_bno_fk
FOREIGN KEY (bno) REFERENCES board(bno) 
on delete cascade
on update cascade;

insert into board_reply(reply, name , bno)
values('aaaaa','aaaaaa',1);

select * from board_reply;

use project;