## 3강

# p4
use kopoctc;						# kopoctc라는 데이터베이스를 선택
drop table if exists examtable;		# examtable라는 테이블이 존재하면 삭제
create table examtable(				# examtable라는 테이블 생성
	name varchar(20),				#   name 필드의 타입은 varchar
    id int not null primary key,	#   id 필드의 타입은 int이며 null을 허용하지 않으며 pk설정
    kor int,						#   kor 필드의 타입은 int
    eng int,						#   eng 필드의 타입은 int
    mat int);						#   mat 필드의 타입은 int
desc examtable;						# examtable 테이블의 구조 확인

#p5
delete from examtable where id>0;													# 기존의 모든 레코드 삭제
insert into examtable value("나연", "209901", rand()*100, rand()*100, rand()*100);	# 20명 학생의 데이터 입력
insert into examtable value("정연", "209902", rand()*100, rand()*100, rand()*100);
insert into examtable value("모모", "209903", rand()*100, rand()*100, rand()*100);
insert into examtable value("사나", "209904", rand()*100, rand()*100, rand()*100);
insert into examtable value("지효", "209905", rand()*100, rand()*100, rand()*100);
insert into examtable value("미나", "209906", rand()*100, rand()*100, rand()*100);
insert into examtable value("다현", "209907", rand()*100, rand()*100, rand()*100);
insert into examtable value("채영", "209908", rand()*100, rand()*100, rand()*100);
insert into examtable value("쯔위", "209909", rand()*100, rand()*100, rand()*100);
insert into examtable value("송가인", "209910", rand()*100, rand()*100, rand()*100);
insert into examtable value("나연", "209911", rand()*100, rand()*100, rand()*100);
insert into examtable value("정연", "209912", rand()*100, rand()*100, rand()*100);
insert into examtable value("모모", "209913", rand()*100, rand()*100, rand()*100);
insert into examtable value("사나", "209914", rand()*100, rand()*100, rand()*100);
insert into examtable value("지효", "209915", rand()*100, rand()*100, rand()*100);
insert into examtable value("미나", "209916", rand()*100, rand()*100, rand()*100);
insert into examtable value("다현", "209917", rand()*100, rand()*100, rand()*100);
insert into examtable value("채영", "209918", rand()*100, rand()*100, rand()*100);
insert into examtable value("쯔위", "209919", rand()*100, rand()*100, rand()*100);
insert into examtable value("송가인", "209920", rand()*100, rand()*100, rand()*100);

#p6
select * from examtable;					# examtable의 모든 레코드의 모든 필드값 조회
select * from examtable order by kor;		# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 오름차순
select * from examtable order by eng;		# examtable의 모든 레코드의 모든 필드값 조회, eng필드값 기준으로 오름차순
select * from examtable order by kor, eng;	# examtable의 모든 레코드의 모든 필드값 조회, kor,eng필드값 기준으로 오름차순
select * from examtable order by kor asc;	# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 오름차순
select * from examtable order by kor desc;	# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 내림차순

#p7
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable;											# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 조회
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable order by kor+eng+mat desc;				# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 합계기준으로 내림차순으로 조회
select *, kor+eng+mat as total, (kor+eng+mat)/3 as average from examtable order by total desc;	# 기존 모든 필드와 더불어 3과목의 합을 "total"이라는 별칭, 3과목의 편균을 "average"라는 별칭으로 조회 하되 total필드를 기준으로 내림차순
select name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 합계,			# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 조회하되 별칭을 주고 합계 기준으로 내림차순
	(kor+eng+mat)/3 as 평균 from examtable order by 합계 desc;
    
 #8  
select * from examtable group by name;  									# 에러
select name, count(name) from examtable group by name; 						# 이름과 이름의 카운트 수를 필드로 조회 하되 name을 그룹으로 묶어서 조회
select * from examtable group by kor;										# 에러 -  조회하고자 명시한 필드에 의해서는 그룹핑이 가능하지 않으므로 에러
select kor, count(kor) from examtable group by kor;							# 국어점수 별로 그룹을 묶어 점수와 점수의 카운트 수를 필드로 조회
select kor, count(kor) from examtable group by eng;							# 에러  - 실재 조회하고자 명시한 필드에 의한 그룹핑은 kor기준이나 group by는 eng이므로 에러 발생
select kor, count(kor), eng, count(eng) from examtable group by kor, eng;	# 국어점수와 영어점수로 그룹으로 묶어서 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 국어와 영어로 그루핑을 했으므로 국어점수와 영어점수 둘다 동일해야 같은 그룹으로 카운트수 증가한다.
select eng, count(eng) from examtable group by eng;							# 영어점수 별로 그룹을 묶어 점수와 점수의 카운트 수를 필드로 조회 

insert into examtable values ( "팽수", 209921, 100, 90, rand()*100);			# 국어점수와 영어점수가 동일한 데이터 입력
insert into examtable values ( "팽수", 209922, 100, 90, rand()*100);
select kor, count(kor), eng, count(eng) from examtable group by kor, eng;	# 국어점수와 영어점수로 그룹으로 묶어서 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 국어와 영어로 그루핑을 했으므로 국어점수와 영어점수 둘다 동일해야 같은 그룹으로 카운트수 증가한다. 
select name, count(name), kor, count(kor), eng, count(eng) from examtable group by name, kor, eng; # 이름과 국어점수와 영어점수로 그룹으로 묶어서 이름과 그 카운트 수, 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 이름, 국어 점수, 영어 점수로 그룹핑을 했으므로 이름과 국어점수와 영어점수이 모두 동일해야 같은 그룹으로 카운트수 증가한다. 
select id, name, count(name), kor, count(kor), eng, count(eng) from examtable group by name, kor, eng; # 에러 - *를 이용하여 테이블 내 모든 필드를 조회하는 것을 추가했으나 id같은 필드는 group by에 의해 그룹화가 되지 않았으므로 에러

select eng, count(eng) from examtable group by eng having count(eng) > 1;  # having절을 추가하여 그룹화된 데이터에 조건을 주어 영어점수 count가 1 초과인 데이터만 조회

select eng, count(eng),(count(eng)/(select count(*) from examtable))*100 from examtable group by eng;	# 영어점수로 그룹화를 하여 해당 영어점수와 그 점수의 카운트 수, 그리고 그 카운트수가 전체 레코드 수 중 차지하는 비율을 조회

#9
drop table if exists tupyo;		# examtable라는 테이블이 존재하면 삭제
create table tupyo(
	name varchar(20),
    age int);
    
select name from examtable group by name;
select name from examtable group by name order by rand() limit 1;
Insert into tupyo values((select name from examtable group by name order by rand() limit 1), rand()*100);

delimiter $$
drop procedure if exists loopinsert $$
create procedure loopinsert()
begin
	declare i int default 1;
    
    while(i < 1000) do
		Insert into tupyo values((select name from examtable group by name order by rand() limit 1), rand()*100);
		set i = i + 1;
	end while;
end $$

call loopinsert();

select * from tupyo;
select count(*) from tupyo;
select name as 이름, count(name)  as 특표수, (count(name) / (select count(*) from tupyo) * 100) as 특표율 from tupyo group by 이름;

select 
	case 
		when age between 0 and 10 then '00대'
		when age between 10 and 20 then '10대'
        when age between 20 and 30 then '20대'
        when age between 30 and 40 then '30대'
        when age between 40 and 50 then '40대'
        when age between 50 and 60 then '50대'
        when age between 60 and 70 then '60대'
        when age between 70 and 80 then '70대'
		when age between 80 and 90 then '80대'
		when age between 90 and 100 then '90대'
        else '그 외'
  end as `연령대`, count(1) as `특표수` from tupyo where name = '다현' group by `연령대` order by 특표수 desc;

select 	name as 이름,
	case 
		when age between 0 and 10 then '00대'
		when age between 10 and 20 then '10대'
        when age between 20 and 30 then '20대'
        when age between 30 and 40 then '30대'
        when age between 40 and 50 then '40대'
        when age between 50 and 60 then '50대'
        when age between 60 and 70 then '60대'
        when age between 70 and 80 then '70대'
		when age between 80 and 90 then '80대'
		when age between 90 and 100 then '90대'
        else '그 외'
  end as `연령대`, count(1) as `특표수` from tupyo group by `연령대`, name order by 이름;