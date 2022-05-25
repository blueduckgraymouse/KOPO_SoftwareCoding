use kopoctc;

drop table if exists hubo_table;
create table hubo_table (
	id int primary key,
    name varchar(20)
);

drop table if exists tupyo_table;
create table tupyo_table (
	id int not null,
    ageRange int not null,
    foreign key(id) references hubo_table(id) on delete cascade
);

insert into hubo_table values(1, "김일돌");
insert into hubo_table values(2, "김이돌");
insert into hubo_table values(3, "김삼돌");
insert into hubo_table values(4, "김사돌");

select * from hubo_table;
select * from tupyo_table;
desc hubo_table;
desc tupyo_table;


select h.id , name, count(h.id), count(h.id)/(select count(*) from tupyo_table) from hubo_table as h, tupyo_table as t where h.id=t.id group by t.id;

select ifnull(count(*), 0) from tupyo_table as t, hubo_table as h where t.id=h.id group by t.id;


# 완성1단계 - - 모든 후보별 득표수
select h.id, ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0) as cnt from hubo_table as h group by id; # 1111
# 완성2단계 - - 모든 후보별 득표수, 율
select h.id, h.name, ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0) as cnt, ifnull((select count(*) from tupyo_table as t where t.id=h.id group by t.id), 0)/(select count(*) from tupyo_table) as rate from hubo_table as h group by id;		# 완성

#
select ageRange, count(ageRange) from tupyo_table where id=1 group by ageRange;

drop table ageRange;
create table ageRange(
	ageRange int primary key
);

insert into ageRange values(1), (2), (3), (4), (5), (6), (7), (8), (9);

select * from ageRange;

select * from tupyo_table where id=1;
select a.ageRange, count(*) from tupyo_table as t, ageRange as a where t.ageRange=a.ageRange and t.id = 1 group by t.ageRange;
# 완성1단계 - 1번 후보의 연령대별 득표수
select a.ageRange, ifnull((select count(*) from tupyo_table as t where t.ageRange=a.ageRange and t.id = 1 group by t.ageRange), 0) as cnt from ageRange as a group by ageRange;
# 완성2단계 - 1번 후보의 연령대별 득표수, 득표율
select a.ageRange, ifnull((select count(*) from tupyo_table as t where t.ageRange=a.ageRange and t.id = 1 group by t.ageRange), 0) as cnt, ifnull((select count(*) from tupyo_table as t where t.ageRange=a.ageRange and t.id = 1 group by t.ageRange), 0)/(select count(*) from tupyo_table where id=1) as rate from ageRange as a group by ageRange;

select * from tupyo_table where id=3;