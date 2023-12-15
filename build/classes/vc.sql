-- 날짜역순, 2 ~ 5번까지 글
select *
from (
	select rownum as rn, gs_no, gs_writer, gs_txt, gs_date, gs_color
	from (
		select *
		from gbraucp2_sns
		where gs_writer like '%%' or gs_txt like '%%'
		order by gs_date desc
	)
)
where rn >= 2 and rn <= 5;
-- 날짜역순, 2 ~ 5번까지 글을 쓴 사람
select gm_id, gm_photo
from gbraucp2_member
where gm_id in (
	select gs_writer
	from (
		select rownum as rn, gs_writer
		from (
			select gs_writer
			from gbraucp2_sns
			where gs_writer like '%%' or gs_txt like '%%'
			order by gs_date desc
		)
	)
	where rn >= 2 and rn <= 5
);

-- join
select gs_no, gs_writer, gs_txt, gs_date, gs_color, gm_photo
from (
	select *
	from (
		select rownum as rn, gs_no, gs_writer, gs_txt, gs_date, gs_color
		from (
			select *
			from gbraucp2_sns
			where gs_writer like '%%' or gs_txt like '%%'
			order by gs_date desc
		)
	)
	where rn >= 2 and rn <= 5
), (
	select gm_id, gm_photo
	from gbraucp2_member
	where gm_id in (
		select gs_writer
		from (
			select rownum as rn, gs_writer
			from (
				select gs_writer
				from gbraucp2_sns
				where gs_writer like '%%' or gs_txt like '%%'
				order by gs_date desc
			)
		)
		where rn >= 2 and rn <= 5
	)
)
where gs_writer = gm_id
order by gs_date desc;





select gs_no, gs_writer, gs_txt, gs_date, gs_color, gm_photo
from gbraucp2_sns, gbraucp2_member
where gs_writer = gm_id
order by gs_date desc;
-----------------------------------------
create table gbraucp2_gallery(
	gg_no number(3) primary key,
	gg_uploader varchar2(10 char) not null,
	gg_title varchar2(50 char) not null,
	gg_file varchar2(100 char) not null,
	gg_date date not null,
	constraint gallery_uploader2
		foreign key (gg_uploader) references y_member(gm_id)
);
create sequence gbraucp2_gallery_seq;
-----------------------------------------
create table gbraucp2_dataroom(
	gd_no number(3) primary key,
	gd_uploader varchar2(10 char) not null,
	gd_title varchar2(50 char) not null,
	gd_file varchar2(100 char) not null,
	gd_category char(6 char) not null,
	gd_date date not null,
	constraint dataroom_uploader2
		foreign key (gd_uploader) 
		references y_member(gm_id)
);
create sequence gbraucp2_dataroom_seq;
select * from gbraucp2_dataroom;
-----------------------------------------
create table gbraucp2_weather_color(
	gwc_temp number(4, 2) not null,
	gwc_humidity number(4, 2) not null,
	gwc_description varchar2(20 char) not null,
	gwc_color char(6 char) not null
);
select * from gbraucp2_weather_color;
-----------------------------------------
create table gbraucp2_sns_reply(
	gsr_no number(4) primary key,
	gsr_gs_no number(4) not null,
	gsr_writer varchar2(10 char) not null,
	gsr_txt varchar2(200 char) not null,
	gsr_date date not null,
	constraint sns2_reply_writer
		foreign key (gsr_writer) references y_member(gm_id)
		on delete cascade,
	constraint sns2_reply
		foreign key (gsr_gs_no) references gbraucp2_sns(gs_no)
		on delete cascade
);
create sequence gbraucp2_sns_reply_seq;
select * from gbraucp2_sns_reply;
-----------------------------------------
create table gbraucp2_sns(
	gs_no number(4) primary key,
	gs_writer varchar2(10 char) not null,
	gs_txt varchar2(500 char) not null,
	gs_date date not null,
	gs_color char(6 char) not null, 	-- 색깔(나중에 AI로 추천)
	constraint sns2_writer
		foreign key (gs_writer) references y_member(gm_id)
		on delete cascade
);
create sequence gbraucp2_sns_seq;
select * from gbraucp2_sns;
-----------------------------------------
create table gbraucp2_member(
	gm_id varchar2(10 char) primary key,
	gm_pw varchar2(10 char) not null,
	gm_name varchar2(10 char) not null,
	gm_birthday date not null,
	gm_address varchar2(200 char) not null,
	gm_photo varchar2(100 char) not null
);

create table y_member(
	gm_id varchar2(12 char) not null primary key,
	gm_pw varchar2(16 char) not null,
	gm_name varchar2(12 char),
	gm_birthday date,
	gm_address varchar2(200 char) ,
	gm_photo varchar2(100 char),
	gm_grade number(1),
	gm_email varchar2(100 char),
	gm_gender varchar2(2 char)
);
select * from y_member;

alter table y_member add mail_auth int default 0;
alter table y_member add mail_key varchar(50);

create table MP_MEMBER_AUTH(MEMBEREMAIL varchar2(100),AUTHKEY varchar2(50));

alter table mp_member add member_auth number default 0;