## 3강

## p4
use kopoctc;						# kopoctc라는 데이터베이스를 선택
drop table if exists examtable;		# examtable라는 테이블이 존재하면 삭제
create table examtable(				# examtable라는 테이블 생성
	name varchar(20),				#   name 필드의 타입은 varchar
    id int not null primary key,	#   id 필드의 타입은 int이며 null을 허용하지 않으며 pk설정
    kor int,						#   kor 필드의 타입은 int
    eng int,						#   eng 필드의 타입은 int
    mat int);						#   mat 필드의 타입은 int
desc examtable;						# examtable 테이블의 구조 확인


## p5
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


## p6
select * from examtable;					# examtable의 모든 레코드의 모든 필드값 조회
select * from examtable order by kor;		# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 오름차순
select * from examtable order by eng;		# examtable의 모든 레코드의 모든 필드값 조회, eng필드값 기준으로 오름차순
select * from examtable order by kor, eng;	# examtable의 모든 레코드의 모든 필드값 조회, kor,eng필드값 기준으로 오름차순
select * from examtable order by kor asc;	# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 오름차순
select * from examtable order by kor desc;	# examtable의 모든 레코드의 모든 필드값 조회, kor필드값 기준으로 내림차순


## p7
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable;											# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 조회
select *, kor+eng+mat, (kor+eng+mat)/3 from examtable order by kor+eng+mat desc;				# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 합계기준으로 내림차순으로 조회
select *, kor+eng+mat as total, (kor+eng+mat)/3 as average from examtable order by total desc;	# 기존 모든 필드와 더불어 3과목의 합을 "total"이라는 별칭, 3과목의 편균을 "average"라는 별칭으로 조회 하되 total필드를 기준으로 내림차순
select name as 이름, id as 학번, kor as 국어, eng as 영어, mat as 수학, kor+eng+mat as 합계,			# 기존 모든 필드와 더불어 3과목의 합, 3과목의 편균을 조회하되 별칭을 주고 합계 기준으로 내림차순
	(kor+eng+mat)/3 as 평균 from examtable order by 합계 desc;
    
    
 ## p8  
select * from examtable group by name;  									# 에러
select name, count(name) from examtable group by name; 						# 이름과 이름의 카운트 수를 필드로 조회 하되 name을 그룹으로 묶어서 조회
select * from examtable group by kor;										# 에러 -  조회하고자 명시한 필드에 의해서는 그룹핑이 가능하지 않으므로 에러
select kor, count(kor) from examtable group by kor;							# 국어점수 별로 그룹을 묶어 점수와 점수의 카운트 수를 필드로 조회
select kor, count(kor) from examtable group by eng;							# 에러  - 실재 조회하고자 명시한 필드에 의한 그룹핑은 kor기준이나 group by는 eng이므로 에러 발생
select kor, count(kor), eng, count(eng) from examtable group by kor, eng;	# 국어점수와 영어점수로 그룹으로 묶어서 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 국어와 영어로 그루핑을 했으므로 국어점수와 영어점수 둘다 동일해야 같은 그룹으로 카운트수 증가한다.
select eng, count(eng) from examtable group by eng;							# 영어점수 별로 그룹을 묶어 점수와 점수의 카운트 수를 필드로 조회 

insert into examtable values ( "팽수", 209921, 100, 90, rand()*100);			# 국어점수와 영어점수가 동일한 데이터 입력
insert into examtable values ( "팽수", 209922, 100, 90, rand()*100);			# 국어점수와 영어점수가 동일한 데이터 입력
select kor, count(kor), eng, count(eng) from examtable group by kor, eng;	# 국어점수와 영어점수로 그룹으로 묶어서 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 국어와 영어로 그루핑을 했으므로 국어점수와 영어점수 둘다 동일해야 같은 그룹으로 카운트수 증가한다. 
select name, count(name), kor, count(kor), eng, count(eng) from examtable group by name, kor, eng; # 이름과 국어점수와 영어점수로 그룹으로 묶어서 이름과 그 카운트 수, 국어점수와 그 카운트수, 영어점수와 그 카운트 수를 필드로 조회
																			# -> 이름, 국어 점수, 영어 점수로 그룹핑을 했으므로 이름과 국어점수와 영어점수이 모두 동일해야 같은 그룹으로 카운트수 증가한다. 
select id, name, count(name), kor, count(kor), eng, count(eng) from examtable group by name, kor, eng; # 에러 - *를 이용하여 테이블 내 모든 필드를 조회하는 것을 추가했으나 id같은 필드는 group by에 의해 그룹화가 되지 않았으므로 에러

select eng, count(eng) from examtable group by eng having count(eng) > 1;  # having절을 추가하여 그룹화된 데이터에 조건을 주어 영어점수 count가 1 초과인 데이터만 조회

select eng, count(eng),(count(eng)/(select count(*) from examtable))*100 from examtable group by eng;	# 영어점수로 그룹화를 하여 해당 영어점수와 그 점수의 카운트 수, 그리고 그 카운트수가 전체 레코드 수 중 차지하는 비율을 조회


##9 - 투표 실습
drop table if exists twice;													# 트와이스 맴버 테이블이 존재하면 테이블 삭제					
create table twice(name varchar(20) primary key);							# 트와이스 맴버 이름을 varchar 타입의 필드로 갖는 테이블 생성
insert into twice values("나연"), ("정연"), ("모모"), ("사나"), ("지효"),			# 트와이스 맴버의 이름 입력
						("미나"), ("다현"), ("채영"), ("쯔위");

drop table if exists tupyo;													# 트표 결과 테이블이 존재하면 테이블 삭제
create table tupyo(															# 투표 결과 테이블 생성
	name varchar(20),														# 득표한 트와이스 맴버 이름을 varchar타입의 필드로 선언
    age int);																# 투표자의 나이를 int타입의 필드로 선언

drop procedure if exists loopinsert;										# 렌덤 투표 1000회 실행하는 프로시저 존재하면 삭제
delimiter $$
create procedure loopinsert()												# 렌덤 투표 1000회 실행하는 프로시저 생성
begin																		# 프로시저의 동작 시작을 나타냄
	declare i int default 0;												# int타입의 i를 0으로 선언
    
    while(i < 1000) do														# i<1000을 만족하는 동안 반복
		Insert into tupyo values((select name from twice group by name order by rand() limit 1), rand()*99+1); # 트와이스 이름을 랜덤으로 이름 테이블에서 뽑아 투표 결과 테이블에 이름과 함께 랜덤 나이를 입력
		set i = i + 1;														# i를 1증가
	end while;																# while반복문 종료
end $$																		# procedure 종료 와 함께 $$ 통해 delimiter 끝남을 명시

call loopinsert();															# loopinsert라는 프로시저를 호출
select name as 후보이름, age as 투표자_나이 from tupyo;							# 투표 결과 테이블 조회

select count(*) as 총_투표수 from tupyo;										# 투표 결과 테이블의 총 레코드 수 확인
select name as 이름, count(name)  as 특표수, (count(name) / (select count(*) from tupyo) * 100) as 특표율 from tupyo group by 이름; # 이름 기준으로 그룹화 한 후, 이름과 득표수와 전체 표 중 득표한 백분율을 조회

select 
	case 										# case문을 통하여 age에 따라 00대~ 90대까지 구분 구룹화한 후 연령대와 카운트 수 조회 하되,
		when age between 0 and 10 then '00대'									# 이름이 나연인 경우만 득표수 기준의 내림차순으로 조회
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
  end as `연령대`, count(*) as `특표수` from tupyo where name = '나연' group by `연령대` order by 특표수 desc;	

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
	end as `연령대`, count(1) as `특표수` from tupyo group by `연령대`, name order by 이름, 연령대;
  
## p11
DROP PROCEDURE IF EXISTS get_sum;		# get_sum이라는 프로지서가 존재하면 삭제
DELIMITER $$
CREATE PROCEDURE get_sum(				# get_sum라는 프로지서 생성
	IN _id integer,						# 입력용 파라미터로 integer형 _id변수 지정
    OUT _name varchar(20),				# 출력용 파라미터로 varchar _name변수 지정
    OUT _sum integer					# 입력용 파라미터로 integer _sum변수 지정
)
BEGIN
	DECLARE _kor integer;				# integer형 _kor 변수 선언
    DECLARE _eng integer;				# integer형 _eng 변수 선언
    DECLARE _mat integer;				# integer형 _mat 변수 선언
    set _kor=0;							# 변수 _kor의 값을 0으로 저장
    
    select name, kor, eng, mat										# examtable 테이블에서 id가 일치하는 레코드의 이름, 국어, 영어, 수학 점수를 조회하여
		into _name, _kor, _eng, _mat from examtable where id =_id;	#  각 해당 변수에 넣어준다.
    
    set _sum = _kor+_eng+_mat;			# _sum의 값을 국어, 영어, 수학 점수의 합으로 저장
END $$									# procedure 종료 와 함께 $$ 통해 delimiter 끝남을 명시
DELIMITER ;								# DELIMITER를 이용하여  끝문자를 ;로 지정

CALL get_sum(209901, @name, @sum);		# get_sum 프로지서 호출, get_sum에서 2,3번째 파라미터는 출력용 파라미터였다.
										# 따라서 실행이 완료되면 name변수와 sum변수에는 해당 값이 저장된다.
                                        # name 변수와 sum변수 앞에 @를 붙여 사용자 정의 변수로 선언해주었으며 life cycle은 세션이 종료될 때까지이다.
select @name, @sum;						# 변수 name과 sum에 저장된 값 조회


## p12
DROP FUNCTION IF EXISTS f_get_sum;		# f_get_sum이라는 프로지서가 존재하면 삭제
DELIMITER $$
CREATE FUNCTION f_get_sum(_id integer) RETURNS integer	# 입력형 파라미터 _id를 갖으며 integer의 값을 반환하는 f_get_sum 프로지서 생성
BEGIN									# 프로시저의 동작 시작을 나타냄
	DECLARE _sum integer;				# integer타입의 _sum변수 선언
    SELECT kor+eng+mat INTO _sum from examtable where id=_id;	# examtable테이블에서 id가 일치하는 레코드의 국어,영어,수학 성적의 합을 _sum변수에 저장
RETURN _sum;							# _sum변수의 값을 반환
END $$									# function 종료 와 함께 $$ 통해 delimiter 끝남을 명시
DELIMITER ;								# DELIMITER를 이용하여  끝문자를 ;로 지정

select *, f_get_sum(id) from examtable;	# examtable 테이블의 모든 필드와 함께 해당 레코드의 id를 파라미터로 f_get_sum 프로시저를 호출하여 반환받는 값을 조회

# 1418 에러 발생시 처리 방법
show global variables like 'log_bin_trust_function_creators';
set global log_bin_trust_function_creators = 'ON';


## p14
drop procedure if exists insert_examtable;			# insert_examtable 프로지서가 존재하면 삭제
delimiter $$
create procedure insert_examtable(_last integer)	# 입력형 파라미터로 integer형 변수 _last를 갖는 insert_examtable 프로지서 생성
begin										# 프로시저의 동작 시작을 나타냄
	declare _name varchar(20);				# varchar타입의 _name변수를 선언
    declare _id integer;					# integer타입의 _id변수를 선언
    declare _cnt integer;					# integer타입의 _cnt변수를 선언
    set _cnt = 0;							# _cnt라는 변수의 값을 0으로 저장
    delete from examtable where id>0;		# examtable 테이블에 존재하는 모든 레코드 삭제
	_loop:loop								# loop 반복문의 시작을 명시
		set _cnt = _cnt+1;					# _cnt 변수 1 증가
		set _name = concat("홍길", cast(_cnt as char(4)));	# _cnt 변수의 값을 크기 4의 char타입으로 형변환 하여 "홍길" 뒤에 이어 뭍여 name 변수에 저장
		set _id = 209900 + _cnt;			# id의 값이 루프를 반복하며 209900부터 1씩 증가
		insert into examtable value (_name, _id, rand()*100, rand()*100, rand()*100);	# examtable 테이블 만들어준 이름과 id와 함께 랜덤 점수 입력
		
		if _cnt = _last then				# _cnt 변수값이 파라미터로 들어온 _last값과 일치하면
			leave _loop;					# 반복문 종료
		end if;								# if 조건무의 종료를 명시
	end loop _loop;							# loop 반복문의 종료를 명시
end $$										# procedure 종료 와 함께 $$ 통해 delimiter 끝남을 명시

call insert_examtable(1000);				# 파라미터값을 1000으로 넣어준 insert_examtable 프로시저 호출
select * from examtable;					# examtable 테이블 조회
select *, kor+eng+mat as sum, (kor+eng+mat)/3 as ave from examtable limit 30, 59;	# examtable테이블의 30~89 번째 레코드의 모든 필드와 합계와 평균 조회


## p15 - 등수 실습
#1
call insert_examtable(100);					# 기존에 만들어 놓은 프로시저를 이용하여 100개의 레코드 입력
select *, (t.kor+t.eng+t.mat) as sum, (t.kor+t.eng+t.mat)/3 as ave from examtable as t;	# 모든 필드와 함께 점수 총점과 평균 확인
select count(*) from examtable as r where (r.kor+r.eng+r.mat) > 139;	# 총점이 139점인 홍길100 보다 총점이 높은 레코드수 카운트 하여 등수 확인

select *,																				# 모든 필드,
	(t.kor+t.eng+t.mat) as sum,															# 합계 점수,
    (t.kor+t.eng+t.mat)/3 as ave,														# 평균 점수,
    (select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum	# 현재 래코드의 총점보다 높은 레코드의 개수를 조회하여 +1 한 값을 rank_sum이라 별칭 하여 조회
	from examtable as t;


#2 - 1 : procedure 버전
drop procedure if exists select_rank;				# insert_examtable 프로지서가 존재하면 삭제
delimiter $$
create procedure select_rank(id integer)			# 입력형 파라미터로 integer형 변수 id를 갖는 select_rank 프로지서 생성
begin												# 프로시저의 동작 시작을 나타냄
	select *,																				# 모든 필드,
		(t.kor+t.eng+t.mat) as sum,															# 합계 점수,
		(t.kor+t.eng+t.mat)/3 as ave,														# 평균 점수,
		(select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum	# 현재 래코드의 총점보다 높은 레코드의 개수를 조회하여 +1 한 값을 rank_sum이라 별칭 하여 조회
		from examtable as t where t.id = id;												# 단, 파라미터로 들어온 id값과 일치하는 레코드만 조회
end $$												# procedure 종료 와 함께 $$ 통해 delimiter 끝남을 명시

call select_rank(209901);							# 학번을 파라미터로 넘겨 학번이 209901인 학생의 등수를 조회하는 프로시저 호출

#2 - 2 : fucntion 버전
drop function if exists f_select_rank;						# f_select_rank 함수가 존재하면 삭제
delimiter $$
create function f_select_rank(p_id integer) returns integer	# 입력형 파라미터로 integer형 변수 id를 갖는 f_select_rank 함수 생성
begin														# 함수의 동작 시작을 나타냄
	DECLARE rank_sum integer;
	select (select count(*) + 1 from examtable as r where r.kor+r.eng+r.mat > t.kor+t.eng+t.mat)
		into rank_sum						
		from examtable as t where t.id = p_id;	# 파라미터로 들어온 id값과 일치하는 레코드의 총점보다 높은 레코드의 개수를 조회하여 +1 한 값을 rank_sum에에 저장
return rank_sum;							# rank_sum 변수의 값을 반환
end $$										# function 종료 와 함께 $$ 통해 delimiter 끝남을 명시
delimiter ;

select *, f_select_rank(id) as rand_sum from examtable;	# 모든 레코드에 대해 모든 필드와 함께 등수도 조회

#3
select *, 
	(t.kor+t.eng+t.mat) as sum, 
    (t.kor+t.eng+t.mat)/3 as ave, 
    (select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as srank
	from examtable as t order by srank;


## p16 - 선호도 조사 프로시저
#1
drop procedure if exists input_data;				# 렌덤 투표 프로시저인 input_data가 존재하면 삭제
delimiter $$
create procedure input_data(size integer)			# integer 타입의 size변수를 파라미터로 받는 프로시저 input_data 생성
begin												# 프로시저의 동작 시작을 나타냄
	declare i int default 0;						# int타입의 i를 0으로 선언
    
    while(i < 1000) do								# i<1000을 만족하는 동안 반복
		Insert into tupyo values((select name from twice group by name order by rand() limit 1), rand()*99+1); # 트와이스 이름을 랜덤으로 이름 테이블에서 뽑아 투표 결과 테이블에 이름과 함께 랜덤 나이를 입력
		set i = i + 1;								# i를 1증가
	end while;										# while반복문 종료
end $$												# procedure 종료 와 함께 $$ 통해 delimiter 끝남을 명시

call input_data(1000);			# 입력할 크기 1000을 파라미터로 input_data 프로시저 호출
select * from tupyo;			# 투표 테이블 조회
select count(*) from tupyo;		# 투표 테이블 레코드 수 조회

	
#2 - 프로시저 버전
drop procedure if exists p_rate;
delimiter $$
create procedure p_rate()
begin
	select name as 이름, count(name)  as 특표수, (count(name) / (select count(*) from tupyo) * 100) as rate from tupyo group by 이름 order by rate desc;
end $$
delimiter ;
call p_rate();

#2 - 함수 버전
drop function if exists f_rate;
delimiter $$
create function f_rate(p_name varchar(20)) returns double
begin
	DECLARE rate double;				# integer타입의 rate 변수 선언
	select (count(name) / (select count(*) from tupyo) * 100) into rate from tupyo where name = p_name group by name;
return rate;
end $$

select *, f_rate(name) from twice;




## p16 - 성적 집계표
#1
call insert_examtable(1000); # 기존에 만들었던 프로시저 이용하여 examtable에 임의의 1000개 데이터 세팅
select * from examtable;
select *, (kor+eng+mat) as sum, (kor+eng+mat)/3 as ave from examtable limit 100, 25;

drop table if exists list_score;
create table list_score as
	select *, (kor+eng+mat) as sum, (kor+eng+mat)/3 as ave from examtable limit 100, 25;
select * from list_score;
select count(*) from list_score;

drop table if exists info_page;
CREATE TABLE info_page as
	select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit 100, 25) as apage;
select * from info_page;

drop table if exists info_page_total;
CREATE TABLE info_page_total as
	select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit 0, 125) as apage;
select * from info_page_total;

# 3개의 테이블을 생성하는 프로시저
drop procedure if exists print_report;
delimiter $$
create procedure print_report(no_page integer, size_page integer)
begin
	declare size_tt integer;
    declare no_max_pg integer;
    declare no_start integer;
    declare no_end integer;
    
    select count(*) into size_tt from examtable;
    set no_max_pg = size_tt / size_page;
    
    if(no_page<1) then
		set no_page = 1;
	elseif (no_page>no_max_pg) then
		set no_page = no_max_pg + 1;
	end if;
	
	set no_page = no_page - 1;
    set no_start = no_page*size_page;
    set no_end = no_start + size_page;

	drop table if exists list_score;
	create table list_score as
		select *, (kor+eng+mat) as sum, (kor+eng+mat)/3 as ave from examtable limit no_start , size_page;

	drop table if exists info_page;
	CREATE TABLE info_page as
        select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit no_start , size_page) as apage;

	drop table if exists info_page_total;
	CREATE TABLE info_page_total as
        select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit 0 , no_end) as apage;
end $$
delimiter ;

call print_report(10, 120);
select * from list_score;
select * from info_page;
select * from info_page_total;

#2
# 3개의 테이블을 생성하는 프로시저
drop procedure if exists print_report;
delimiter $$
create procedure print_report_with_rank(no_page integer, size_page integer)
begin
	declare size_tt integer;
    declare no_max_pg integer;
    declare no_start integer;
    declare no_end integer;
    
    select count(*) into size_tt from examtable;
    set no_max_pg = size_tt / size_page;
    
    if(no_page<1) then
		set no_page = 1;
	elseif (no_page>no_max_pg) then
		set no_page = no_max_pg + 1;
	end if;
	
	set no_page = no_page - 1;
    set no_start = no_page*size_page;
    set no_end = no_start + size_page;

	drop table if exists list_score_with_rank;
	create table list_score_with_rank as
		select *,																				# 모든 필드,
			(t.kor+t.eng+t.mat) as sum,															# 합계 점수,
			(t.kor+t.eng+t.mat)/3 as ave,														# 평균 점수,
			(select count(*)+1 from examtable as r where r.kor+r.eng+r.mat > sum) as rank_sum	# 현재 래코드의 총점보다 높은 레코드의 개수를 조회하여 +1 한 값을 rank_sum이라 별칭 하여 조회
			from examtable as t limit no_start , size_page;

	drop table if exists info_page;
	CREATE TABLE info_page as
        select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit no_start , size_page) as apage;

	drop table if exists info_page_total;
	CREATE TABLE info_page_total as
        select sum(kor) as kor_sum, sum(eng) as eng_sum, sum(mat) mat_sum, sum(kor+eng+mat) as sum_sum, sum((kor+eng+mat)/3) as sum_avg, avg(kor) as avg_kor, avg(eng) as avg_eng, avg(mat) as avg_mat, avg(kor+eng+mat) as avg_sum, avg((kor+eng+mat)/3) as avg_avg from (select * from score limit 0 , no_end) as apage;
end $$
delimiter ;

call print_report_with_rank(10, 120);
select * from list_score_with_rank;
select * from info_page;
select * from info_page_total;