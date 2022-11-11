create database project;
use project;

-- drop table member;
create table member(
id varchar(100) primary key,
password varchar(100) not null,
name varchar(100) not null,
point int default 0
);

select * from member;

delete from member where id='asdf1234';


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

-- drop table board;
create table board(
bno int auto_increment primary key,
title varchar(1000) not null,
content varchar(10000) 
);
insert into board(title,content)
values('첫글','첫글입니다');

select * from board;
desc board;
