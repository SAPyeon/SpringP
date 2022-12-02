create database project;

use project;

-- 회원테이블
drop table member;
create table member(
id varchar(100) primary key, -- 아이디 
password varchar(100), -- 비밀번호 
name varchar(100) not null, -- 이름
phone varchar(20) not null, -- 전화번호
point int default 0, -- 포인트 
authority boolean default false, -- 관리자여부 
authno varchar(100) not null default 30
);

desc member;

insert into member(id,password,name,phone,authority,authno)
values('asdf1234','asdf1234','admin','11111',true, 10);

select * from member;

-- 멤버 등급 테이블
-- drop table authorities;
create table authorities(
	authno varchar(100) primary key,
    authority varchar (100) not null
);

select * from authorities;

insert into authorities(authno, authority)
values('10','manager'),('20','admin'),('30','member');

-- 등급업 테이블
create table changeAuth(
	id varchar(100), 
    beforeAuthno varchar(100),
    AfterAuthno varchar(100),
    ReasonChange varchar(10000),
    regdate datetime default now(),
    allow boolean default false
);

select * from changeAuth;

delete from changeAuth where regdate = "2022-12-02 15:05:37";
 
-- 회원탈퇴 테이블
create table Withdrawal(
	wno int auto_increment primary key, -- 탈퇴데이터 번호 
	id varchar(100), -- 아이디 
    reason varchar(10000), -- 탈퇴 사유 
    regdate datetime default now() -- 작성일
);
select * from withdrawal;





-- 주가정보 테이블
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

-- kospi 회사 정보테이블
-- drop table companyInfo;
create table companyInfo(
StaticCode varchar(30) primary key, -- 표준코드
code varchar(20) unique key, -- 단축코드
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

-- 커뮤니티 게시판 테이블
drop table board;
create table board(
bno int auto_increment primary key, -- 게시판번호
title varchar(1000) not null, -- 글제목
content varchar(10000), -- 게시판 내용 
name varchar(100), -- 작성자 이름
id varchar(100), -- 작성자 아이디 
cnt int default 0, -- 조회수 
regdate datetime default now(), -- 작성일
notice boolean default false -- 공지사항여부
);
insert into board(title, content, name, id, notice)
values('작성','게시글작성','aaaaa','1111',true);

desc board;
select count(*) from board where id="asdf1234" order by bno;
desc board;
select * from board;


-- 커뮤니티 댓글 테이블
-- drop table board_reply;
create table board_reply(
rno int auto_increment primary key, -- 리뷰번호
reply varchar(10000), -- 리뷰글
name varchar(100) not null, -- 리뷰작성자
id varchar(100) not null,
declaration int default 0, -- 신고
regdate datetime default now(), -- 작성일
bno int, -- 게시판번호
nno int -- 공지사항 번호
);

desc board_reply;

ALTER TABLE board_reply ADD CONSTRAINT board_bno_fk
FOREIGN KEY (bno) REFERENCES board(bno) 
on delete cascade
on update cascade;

-- update시 시간변경
ALTER TABLE board_reply CHANGE regdate regdate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;

update board_reply set id = "zxcvzxcv" where rno= 2;

insert into board_reply(reply, name , bno)
values('aaaaa','aaaaaa',1);

select * from board_reply where bno=4;



-- 게시판 신고테이블
-- drop table declaration;
create table declaration(
	id varchar(100),
    rno int,
    bno int,
    reason varchar(10000),
    regdate datetime default now()
);

-- primary key가 아님, 외래키 설정 불가
ALTER TABLE declaration ADD CONSTRAINT board_reply_rno_fk2
FOREIGN KEY (rno) REFERENCES board_reply(rno) 
on delete cascade
on update cascade;

select * from declaration;

delete from declaration where rno=11;

select * 
from declaration d
join board_reply br
on d.rno = br.rno
order by d.regdate desc;


use project;

-- 주식정보 즐겨찾기 테이블
drop table stockLike;
create table stockLike(
id varchar(100) not null, -- 아이디
srtnCd  varchar(50) not null, -- 종목코드 
itmsNm varchar(100) not null, -- 종목이름
diffAmount varchar(1000) not null, -- 전일비
dayRange varchar(1000) not null, -- 등락률
parValue  varchar(1000) not null, -- 액면가
marketCap varchar(1000) not null, -- 시가총액
numberOfListedShares varchar(1000) not null, -- 상장주식수
foreignOwnRate varchar(1000) not null, -- 외국인비율
turnover varchar(1000) not null, -- 거래량
per varchar(1000) not null -- per
);

ALTER TABLE stockLike ADD CONSTRAINT StockInfo_srtnCd_fk2
FOREIGN KEY (srtnCd) REFERENCES StockInfo(srtnCd) 
on delete cascade
on update cascade;

ALTER TABLE stockLike ADD CONSTRAINT member_id_fk1
FOREIGN KEY (id) REFERENCES member(id) 
on delete cascade
on update cascade;

select * from stockLike;

-- insert into stockLike(id, srtnCd,itmsNm)
-- values("asdf1234","005930","삼성전자");

select * from stockLike
where id ='asdf1234';


use project;


