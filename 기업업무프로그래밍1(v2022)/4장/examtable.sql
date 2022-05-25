show tables;
select * from examtable;
drop table if exists examtable;

create table if not exists examtable(name varchar(20), studentid int not null primary key, kor int, eng int, mat int);

select * from examtable;
desc examtable;

insert ignore into examtable (name, studentid, kor, eng, mat) values('나연',209913, 95, 100, 95);

select * from examtable where name = '나연';


select studentid+1 from examtable where (studentid+1) not in (select studentid from examtable);

insert into examtable (name, studentid, kor, eng, mat) values('나연',209913, 95, 100, 95);

delete from examtable where studentid=209911;

insert into examtable values('xptmxm', 209929, null, null, null);

update examtable set name="홍길02", kor=33, eng=33, mat=33 where studentid=209935;



select *,																				# 모든 필드,
		(t.kor+t.eng+t.mat) as sum,															# 합계 점수,
		(t.kor+t.eng+t.mat)/3 as ave,														# 평균 점수,
		(select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum	# 현재 래코드의 총점보다 높은 레코드의 개수를 조회하여 +1 한 값을 rank_sum이라 별칭 하여 조회
		from examtable as t limit 0, 20;
        
delete from examtable where studentid > 209909;
delete ignore from examtable where studentid >= 209901 and studentid <= 209909;

select studentid+1 from examtable where (studentid+1) not in (select studentid from examtable);


