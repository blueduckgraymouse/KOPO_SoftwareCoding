use kopoctc;

# p6
drop table if exists hubo;	# hubo 테이블 존재시 테이블 삭제
create table hubo(			# hubo 테이블 생성
	kiho int not null,		# int타입의 kiho 필드 not null로 지정하여 추가
    name varchar(10),		# varchar타입의 name 필드 추가
    gongyak varchar(50),	# varchar타입의 gongyak 필드 추가
    primary key(kiho),		# kiho필드 pk로 지정
    index(kiho));			# kiho필드 인덱스로 지정
desc hubo;					# hubo 테이블의 필드 정보 조회

drop table if exists tupyo;	# tupyo 테이블 존재시 테이블 삭제
create table tupyo(			# hubo tupyo 생성
	kiho int,				# intr타입의 kiho 필드 추가
    age int,				# intr타입의 age 필드 추가
    foreign key(kiho) references hubo(kiho)); # 생성할 테이블의 kiho필드가 hubo테이블의 kiho 필드를 참조하는 외래키로 설정
desc tupyo;					# tupyo 테이블의 필드 정보 조회



# p7
delete from hubo where kiho>0;					# hubo 테이블의 모든 레코드 삭제
insert into hubo values(1, "나연", "정의사회 구현");	# 트와이스 맴버의 기호와 이름, 공약을 모두 입력
insert into hubo values(2, "정연", "모두 1억 줌");
insert into hubo values(3, "모모", "월화수목토토일");
insert into hubo values(4, "사나", "살맛나는 세상, 비계맛도 조금");
insert into hubo values(5, "지효", "먹다 지쳐 잠드는 세상");
insert into hubo values(6, "미나", "나 뽑으면 너하고 싶은거 다해");
insert into hubo values(7, "다현", "장바구니 다 사줄께");
insert into hubo values(8, "채영", "노는게 잴조은 뽀로로세상 구현");
insert into hubo values(9, "쯔위", "커플지옥 싱글 파라다이스");
select kiho as 기호, name as 성명, gongyak as 공약 from hubo;	# 각 필드에 별칭을 주어 kiho의 모든 레코드 조회



# p8
delete from tupyo where kiho>0;			# tupyo테이블에 모든 레코드 삭제
drop procedure if exists insert_tupyo;	# insert_tupyo 프로시저가 존재하면 프로시저 삭제
delimiter $$
create procedure insert_tupyo(_limit integer)	# integer타입의 _limit 파라미터를 갖는 insert_tupyo 프로시저 생성
begin									# 프로시저 내용의 시작을 명시
	declare _cnt integer;				# integer의 _cnt변수 선언
    set _cnt=0;							# _cnt 변수에 0 저장
	_loop:loop							# loop반복문인 _loop 시작
		set _cnt = _cnt + 1;			# _cnt 1 증가
		insert into tupyo value (rand()*8+1, rand()*8+1); # 임의의 기호번호와 임의의 연령대를 tupyo 테이블에 입력
		if _cnt = _limit then			# _cnt 변수값이 파라미터로 들어온 _limit의 값과 같다면
			leave _loop;				# _loop 반복문 종료
		end if;							# if 조건문의 끝을 명시
	end loop _loop;						# loop 반복문의 끝을 명시
end $$									# 프로시저의 끝과 delimiter의 끝을 명시

call insert_tupyo(1000);				# 1000을 파라미터로 insert_tupyo 프로시저 호출하여 임의의 1000개 데이터 입력
select kiho as 투표한기호, age as 투표자연령대 from tupyo;	# 별칭을 이용하여 tupyo 테이블의 전제 레코드 조회



# p9
select kiho as 기호, name as 성명, gongyak as 공약 from hubo;	# 별칭을 이용하여 hubo 테이블의 전체 레코드 조회
select kiho as 투표한기호, age as 투표자연령대 from tupyo;	# 별칭을 이용하여 tupyo 테이블의 전체 레코드 조회



# p10
select kiho, count(*) from tupyo group by kiho;		# 각 기호 번호가 몇 개의 표를 받았는지 확인



# p11
select h.name, h.gongyak, count(t.kiho) 	# hubo 테이블의 name과 hubo 테이블의 공약과 tupyo 테이블의 kiho를 조회할 필드로 설정
	from tupyo as t, hubo as h 				# tupyo 테이블의 별칭을 t, hubo 테이블의 별칭을 h로 설정
	where t.kiho = h.kiho 					# tupyo 테이블의 kiho와 hubo 테이블의 kiho가 같은 레코드에 한하여
    group by t.kiho;						# tupyo 테이블의 kiho를 기준으로 그룹화하여 조회
    
    
    
# p12
select
	(select name from hubo where kiho=t.kiho),		# hubo 테이블의 kiho 필드값과 outer 밖에 위치한 별칭 t인 tuho 테이블의 kiho 필드값이 일치하는 name 필드값 조회하여 필드로 지정
    (select gongyak from hubo where kiho=t.kiho),	# hubo 테이블의 kiho 필드값과 outer 밖에 위치한 별칭 t인 tuho 테이블의 kiho 필드값이 일치하는 gongyak 필드값 조회하여 필드로 지정
    count(t.kiho)									# 별칭 t인 tuho 테이블의 kiho 필드의 레코드 수를 필드로 지정
    from tupyo as t			# tupyo 테이블의 별칭을 t로 지정하여 조회 대상 테이블로 지정
    group by t.kiho;		# tupyo 테이블의 kiho필드를 기준으로 그룹화하여 조회

# p13 - 호감도 투표2 - 인당 3명 투표
# 1 - 테이블 생성
drop table if exists tupyo2;	# tupyo2테이블 존재하면 삭제
create table tupyo2(			# tupyo2 테이블 생성
	kiho1 int,					# integer타입의 첫번째 투표한 맴버 번호 필드
    kiho2 int,					# integer타입의 두번째 투표한 맴버 번호 필드
    kiho3 int,					# integer타입의 세번째 투표한 맴버 번호 필드
    age int);					# integer타입의 투표한 사람의 나이 연령대
desc tupyo2;					# tupyo2테이블의 필드 정보 출력

# 2 - 랜덤 투표 1000회 프로시저 만들기
drop procedure if exists insert_tupyo2;			# insert_tupyo2 테이블 존재하면 삭제
delimiter $$				
create procedure insert_tupyo2(size integer)	# 투표횟수 size를 integer타입의 파라미터로 받는 insert_tupyo2 프로시저 생성
begin											# 프로시저의 시작을 명시
	declare i integer default 0;				# integer타입의 변수 i를 0으로 초기화
    
    loop_insert:loop							# 루프 반복문 시작
		set i = i + 1;							# i 1증가
        insert into tupyo2 values(rand()*8+1, rand()*8+1, rand()*8+1, rand()*8+1);	# 랜덤의 투표 기호 3개와 렌덤의 연령대 입력
		if i = size then						# 파리머터로 들어온 1000번 입력되었으면
			leave loop_insert;					#   루프 반복문 종료
		end if;									# 조건문 끝을 명시
	end loop loop_insert;						# 루프 반복문 끝을 명시
end $$											# 프로지서와 delimiter의 끝을 명시

call insert_tupyo2(1000);						# 렌덤 투표 1000회 실행
select * from tupyo2;							# 투표 결과 확인
select count(*) from tupyo2;					# 투표 1000번 값 잘 실행되었는지 확인
    
# 3 - 투표 결과 테이블의 기호를 이름으로 바꾸는 쿼리 작성
select t.age as 투표자_연령대, h1.name as 투표1, h2.name as 투표2, h3.name as 투표3	# 각 투표 번호마다 별도의 hubo테이블에 접근하여
	from tupyo2 as t, hubo as h1, hubo as h2, hubo as h3						# 후보 번호와 후보 이름이 동일한 후보 이름을 가져와 조회
    where t.kiho1 = h1.kiho and t.kiho2 = h2.kiho and t.kiho3 = h3.kiho;		# 단 출력되는 하나의 레코드 모두 후보 번호와 후보 이름이 일치해야 한다.

# 4 - 3번과 동일하지만 select 안에 select 형태로 쿼리 작성
select
	age as 투표자_연령대,										# tupyo2의 age 필드값 출력
	(select name from hubo where kiho = kiho1) as 투표1,		# 투표 결과 테이블의 첫번째 투표 기호와 후보 테이블의 후보 기호가 일치하는 레코드의 name 조회하여 필드값으로 출력
	(select name from hubo where kiho = kiho2) as 투표2,		# 투표 결과 테이블의 두번째 투표 기호와 후보 테이블의 후보 기호가 일치하는 레코드의 name 조회하여 필드값으로 출력
    (select name from hubo where kiho = kiho3) as 투표3		# 투표 결과 테이블의 세번째 투표 기호와 후보 테이블의 후보 기호가 일치하는 레코드의 name 조회하여 필드값으로 출력
    from tupyo2;											# tupyo2테이블의 모든 레코드에 대해 조회 실행

# p15
# 5 - 모든 맴버의 특표수 출력
select
	(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1) as 나연,	# 투표 결과 레코드에서 한번이라도 1번을 찍은 적이 있는레코드 카운트
	(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2) as 정연,	# 투표 결과 레코드에서 한번이라도 2번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3) as 모모,	# 투표 결과 레코드에서 한번이라도 3번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4) as 사나,	# 투표 결과 레코드에서 한번이라도 4번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5) as 지효,	# 투표 결과 레코드에서 한번이라도 5번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6) as 미나,	# 투표 결과 레코드에서 한번이라도 6번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7) as 다현,	# 투표 결과 레코드에서 한번이라도 7번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8) as 채영,	# 투표 결과 레코드에서 한번이라도 8번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9) as 쯔위;	# 투표 결과 레코드에서 한번이라도 9번을 찍은 적이 있는레코드 카운트
/*
drop procedure if exists sum_tupyo;
delimiter $$
create procedure sum_tupyo()
begin
	declare i integer default 1;
    declare j integer;
	declare size integer default 0;
    select count(*) into size from hubo;
    
    delimiter $$$
    select(
	while i <= size do
		set j = 0;
		select count(*) from tupyo2 where
        while  j < 3 do
			kiho1 = i
            if j != 3 then
				or
			end if;
			),
		end while;
	end while;
    $$$
end $$ */

# p16
# 6 - 총 특표수와 함께 중복 투표 분석한 결과까지 출력
select
(select count(*) from tupyo2 where kiho1=1 or kiho2=1 or kiho3=1) as 나연,		# 투표 결과 레코드에서 한번이라도 1번을 찍은 적이 있는레코드 카운트
	(select count(*) from tupyo2 where kiho1=2 or kiho2=2 or kiho3=2) as 정연,	# 투표 결과 레코드에서 한번이라도 2번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=3 or kiho2=3 or kiho3=3) as 모모,	# 투표 결과 레코드에서 한번이라도 3번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=4 or kiho2=4 or kiho3=4) as 사나,	# 투표 결과 레코드에서 한번이라도 4번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=5 or kiho2=5 or kiho3=5) as 지효,	# 투표 결과 레코드에서 한번이라도 5번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=6 or kiho2=6 or kiho3=6) as 미나,	# 투표 결과 레코드에서 한번이라도 6번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=7 or kiho2=7 or kiho3=7) as 다현,	# 투표 결과 레코드에서 한번이라도 7번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=8 or kiho2=8 or kiho3=8) as 채영,	# 투표 결과 레코드에서 한번이라도 8번을 찍은 적이 있는레코드 카운트
    (select count(*) from tupyo2 where kiho1=9 or kiho2=9 or kiho3=9) as 쯔위,	# 투표 결과 레코드에서 한번이라도 9번을 찍은 적이 있는레코드 카운트
	(select 나연+정연+모모+사나+지효+미나+다현+채영+쯔위) as "총합",						# 위에서 별칭을 준 각 맴버의 득표수의 합
    (select count(*) from tupyo2 where kiho1=kiho2 or kiho2=kiho3 or kiho1=kiho3) as "중복2",	# 한 사람이 한 후보에 2표를 투표한 경우에 해당하는 표의 수
    (select count(*) from tupyo2 where kiho1=kiho2 and kiho2=kiho3) as "중복3";	# 한 사람이 한 후보에 3표를 투표한 경우에 해당하는 표의 수



# p17 - View와 insert안에 select
use kopoctc;
drop table if exists examtable;		# examtable테이블 존재하면 삭제
create table examtable(				# examtable 테이블 생성
	name varchar(20),				# varchar타입의 name 필드 추가
	id int not null primary key,	# int타입의 id 필드 null 허용하지 않고 pk로 지정하여 추가 (pk는 당연히 null을 허용하지 않으므로 not null 굳이 선언할 필요X)
    kor int, eng int, mat int);		# int타입의 kor, eng, mat 필드 추가
desc examtable;						# examtable테이블의 필드 정보 조회
delete from examtable where id>0;					# examtable테이블 내 모든 레코드 삭제
drop procedure if exists insert_examtable;			# insert_examtable프로시저 존재하면 삭제
delimiter $$										
create procedure insert_examtable(_limit integer)	# integer타입의 _limit 파라미터를 갖는 insert_examtable 프로시저 생성
begin								# 프로시저 시작을 명싱
	declare _name varchar(20);		# varchar타입의 _name변수 선언
	declare _id integer;			# integer타입의 _id변수 선언
	declare _cnt integer;			# integer타입의 _cnt변수 선언
    set _cnt=0;						# _cnt 변수 0에 0 저장
	_loop:loop						# 루프 반복문 시작
		set _cnt=_cnt+1;			# _cnt 변수 1증가
		set _name =concat("홍길", cast(_cnt as char(4)));	# "홍길" 뒤에 연속된 숫자 _cnt를 붙여 _name에 저장
		set _id=209900+_cnt;		# 209900부터 증가하는 숫자 _id변수에 저장
		
		insert into examtable value(_name, _id, rand()*100, rand()*100, rand()*100);	# 임의의 점수를 갖는 레코드 입력
		
		if _cnt=_limit then			# _cnt가 파라미터로 들어온 _limit와 같으면
			leave _loop;			#   루프 반복문 종료
		end if;						#   조건문 종료를 명시
	end loop _loop;					# 반복문 종료를 명시
end $$								# 프로시저와 delimiter의 종료를 명시
call insert_examtable(1000);		# insert_examtable프로시저를 호출하여 임의의 레코드 1000개 입력
select * from examtable;			# examtable 테이블 조회
        
select count(*) from examtable;		# examtable 테이블의 종 레코드 수 조회

# p18 - view 실습
drop view if exists examview;		# examview라는 이름의 view가 존재하면 삭제
create view examview(name, id, kor, eng, mat, tot, ave, ran)	# 8개의 파라미터를 받는 examview 뷰 생성
	as									# select문으로 필드를 기준으로 조회된 모든 레코드를 view로 저장
		select *,						# examtable의 기존 모든 필드 추가
			b.kor + b.eng + b.mat,		# 파라미터로 들어온 3과목의 점수의 합을 필드로 추가
            (b.kor + b.eng + b.mat)/3,	# 파라미터로 들어온 3과목의 점수의 평균을 필드로 추가
            (select count(*) + 1 from examtable as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat))	# 현재 레코드의 합보다 큰 합계의 개수를 세어 등수 필드로 추가
			from examtable as b;		# examtable 테이블의 모든 레코드에 대하여 조회
# p19
select * from examview;					# examview 조회
select name, ran from examview;			# 이름과 등수만 examview에서 조회
select count(*) from examtview;			# 뷰에 저장된 총 레코드 개수 확인 => 에러, 불가

select * from examview where ran>990;	# 등수가 990초과인 레코드만 조회
insert into examview values("나연", 309933, 100, 100, 100, 300, 100, 1);		# 에러 - view에는 입력 불가


# p20 - insert 안에 select
drop table if exists examtableEX;		# examtableEX테이블 존재하면 삭제
create table examtableEX(				# examtableEX테이블 생성
	name varchar(20),					# varchar타입의 name필드 추가
    id int not null primary key,		# int타입의 id필드 null을 허용하지 않고 ok로 설정하여 추가
    kor int, eng int, mat int, sum int, ave double, ranking int); # int타입의 kor, eng, mat, sum, ave, ranking 필드 추가
desc examtableEX;						# examtableEX 테이블의 필드 정보 초회

insert into examtableEX					# examtableEX 테이블에 입력
	select *, b.kor+b.eng+b.mat, (b.kor+b.eng+b.mat)/3,		# examtable 테이블의 모든 필드와 3과목 성적 합과 평균 조회한 필드 &
    (select count(*)+1 from examtable as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat))	# 현재 레코드의 총합보다 큰 총합을 센 등수 필드를 입력
    from examtable as b;				# examtable의 모든 레코드에 대하여 입력
    
select * from examtableEX order by ranking desc;	# examtableEX테이블의 모든 레코드를 ranking 기준으로 내림차순 정렬 조회
select count(*) from examtableEX;		# examtableEX 테이블 총 레코드 수 확인




# ------------------------------------------------------------------------------------------------------------------------


-- # p22
-- #1
-- # 답안 테이블 생성
-- drop table exam_kor_answer;
-- create table exam_kor_answer(
-- 	no int primary key,
--     answer int);
-- drop table exam_eng_answer;
-- create table exam_eng_answer(
-- 	no int primary key,
--     answer int);
-- drop table exam_mat_answer;
-- create table exam_mat_answer(
-- 	no int primary key,
--     answer int);

-- drop procedure if exists make_answer;
-- delimiter $$
-- create procedure make_answer()
-- begin
-- 	declare i integer default 1;
--     
--     delete from exam_kor_answer where i > 0;
-- 	while i < 21 do
-- 		insert into exam_kor_answer values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_eng_answer where i > 0;
-- 	while i < 21 do
-- 		insert into exam_eng_answer values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_mat_answer where i > 0;
-- 	while i < 21 do
-- 		insert into exam_mat_answer values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
-- end $$

-- call make_answer();

-- select * from exam_kor_answer;
-- select * from exam_eng_answer;
-- select * from exam_mat_answer;



-- #2 학생의 답안
-- drop table exam_kor;
-- create table exam_kor(
-- 	no int primary key,
--     answer int);
-- drop table exam_eng;
-- create table exam_eng(
-- 	no int primary key,
--     answer int);
-- drop table exam_mat;
-- create table exam_mat(
-- 	no int primary key,
--     answer int);

-- drop procedure if exists make_exam;
-- delimiter $$
-- create procedure make_exam()
-- begin
-- 	declare i integer default 1;
--     
--     delete from exam_kor where i > 0;
-- 	while i < 21 do
-- 		insert into exam_kor values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_eng where i > 0;
-- 	while i < 21 do
-- 		insert into exam_eng values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_mat_answer where i > 0;
-- 	while i < 21 do
-- 		insert into exam_mat values(i, rand()*5 + 1);
--         set i = i + 1;
-- 	end while;
-- end $$

-- call make_exam();

-- select * from exam_kor;
-- select * from exam_eng;
-- select * from exam_mat;


-- # 3
-- drop table exam_kor_boolean;
-- create table exam_kor_boolean(
-- 	no int primary key,
--     tf boolean);
-- drop table exam_eng_boolean;
-- create table exam_eng_boolean(
-- 	no int primary key,
--     tf int);
-- drop table exam_mat_boolean;
-- create table exam_mat_boolean(
-- 	no int primary key,
--     tf int);
--     
-- drop procedure if exists check_exam;
-- delimiter $$
-- create procedure check_exam()
-- begin
-- 	declare i integer default 1;
--     declare answer_student integer;
--     declare answer_exam integer;
--     
--     delete from exam_kor_boolean where i > 0;
-- 	while i < 21 do
-- 		select answer into answer_student from exam_kor where no = i;
-- 		select answer into answer_exam from exam_kor_answer where no = i;
--         if answer_student = answer_exam then
-- 			insert into exam_kor_boolean values(i, true);
-- 		else
-- 			insert into exam_kor_boolean values(i, false);
--         end if;
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_eng_boolean where i > 0;
-- 	while i < 21 do
-- 		select answer into answer_student from exam_eng where no = i;
-- 		select answer into answer_exam from exam_eng_answer where no = i;
--         if answer_student = answer_exam then
-- 			insert into exam_eng_boolean values(i, true);
-- 		else
-- 			insert into exam_eng_boolean values(i, false);
--         end if;
--         set i = i + 1;
-- 	end while;
--     set i = 1;
--     delete from exam_mat_boolean where i > 0;
-- 	while i < 21 do
-- 		select answer into answer_student from exam_mat where no = i;
-- 		select answer into answer_exam from exam_mat_answer where no = i;
--         if answer_student = answer_exam then
-- 			insert into exam_mat_boolean values(i, true);
-- 		else
-- 			insert into exam_mat_boolean values(i, false);
--         end if;
--         set i = i + 1;
-- 	end while;
-- end $$

-- call check_exam();

-- select * from exam_kor_boolean;
-- select * from exam_eng_boolean;
-- select * from exam_mat_boolean;

# -----------------------------------------------------------------------------------------------------------------------

# p22 - 시험 처리 실습
#1 - 테이블 생성
drop table if exists answer;			# answer테이블 존재시 삭제
create table answer(					# answer테이블 생성
	subjectid int not null primary key,		# int타입의 subjectid 필드 null 허용하지 않고 pk로 설정하여 추가
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,	# 1~10번 문제에 대한 답 필드로 추가
	a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int);	# 11~20번 문제에 대한 답 필드로 추가
desc answer;							# answer테이블의 필드 정보 조회

drop table if exists testing;				# testing테이블 존재시 삭제
create table testing(						# testing테이블 생성
	subjectid int not null,					# int타입의 subjectid 필드 null 허용하지 않고 추가
	stu_name varchar(20),					# varchar타입의 stu_name 필드 추가
	stu_id int not null,					# int타입의 stu_id 필드 null 허용하지 않고 추가
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,	# 1~10번 문제에 대한 답 필드로 추가
	a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,	# 11~20번 문제에 대한 답 필드로 추가
	primary key(subjectid, stu_id));			# subjectid와 stu_id 필드를 pk로 설정
desc testing;								# answer테이블의 필드 정보 조회

drop table if exists scoring;				# scoring테이블 존재시 삭제
create table scoring(						# scoring테이블 생성
	subjectid int not null,					# int타입의 subjectid 필드 null 허용하지 않고 추가
	stu_name varchar(20),					# varchar타입의 stu_name 필드 추가
	stu_id int not null,					# int타입의 stu_id 필드 null 허용하지 않고 추가
	a01 int, a02 int, a03 int, a04 int, a05 int, a06 int, a07 int, a08 int, a09 int, a10 int,	# 1~10번 문제에 대한 답 필드로 추가
	a11 int, a12 int, a13 int, a14 int, a15 int, a16 int, a17 int, a18 int, a19 int, a20 int,	# 11~20번 문제에 대한 답 필드로 추가
	score int,								# int타입의 score 필드 추가
	primary key(subjectid, stu_id));		# subjectid와 stu_id 필드를 pk로 설정
desc scoring;								# scoring테이블의 필드 정보 조회

drop table if exists report_table;			# scoring테이블 존재시 삭제
create table report_table(					# scoring테이블 생성
	stu_name varchar(20),					# varchar타입의 stu_name 필드 추가
	stu_id int not null primary key,		# int타입의 stu_id 필드 null 허용하지 않고 pk로 설정하여 추가
	kor int, eng int, mat int);				# int타입의 kor, eng, mat 필드 추가
desc scoring;								# scoring테이블의 필드 정보 조회

desc answer;
desc testing;
desc scoring;
desc report_table;



#2 - 실습데이터 만들기
# answer 테이블 채우기
drop procedure if exists input_answer;				# input_answer프로시저 존재하면 삭제
delimiter $$	
create procedure input_answer(size_subject integer, size_p integer)	# integer타입의 과목수와 문제수를 파라미터 받는 input_answer 생성
begin												# 프로시저의 시작을 명시
	declare i, j integer default 1;					# integer형 변수 i, j를 1로 초기화
    declare k integer default 0;					# integer형 변수 k를 0로 초기화
    declare name_a varchar(20);						# 문제번호 필드명이 저장될 name_a 변수 varchar타입으로 선언
    declare num_r double;							# 임의의 정답 번호가 저장될 num_r 변수 double타입으로 선언
    
    delete from answer where subjectid > 0;			# answer테이블에 존재하는 모든 데이터 삭제
	while i <= size_subject do						# 과목 수 만큼 while문 반복
		insert into answer(subjectid) value(i);		# answer테이블에 과목번호만 저장된 레코드 입력\
        set j = 1;
        while j <= size_p do								# 문제 개수인 20 회 반복
			set name_a = concat("a", cast(LPAD(j, 2, 0) as char(4)));	# i를 2자리 수의 숫자로 변환하여 name_a 변수에 저장, 한자리 숫자면 십의 자리에 0을 재운다.
            set num_r = rand()*4+1;										# 임의의 정답에 해당하는 숫자를 num_r변수에 저장
            set @query_txt = concat('update answer set ', name_a, '=', num_r, ' where subjectid =', i, ';');	# 동적 update쿼리문을 만들어 @query_txt변수에 저장
            prepare stmt from @query_txt;			# @query_txt 변수에 저장된 쿼리문을 statment에 저장
			execute stmt;							# statment에 저장된 쿼리 실행
            set j = j + 1;							# 문제 번호로 변활될 j 1증가
        end while;
		set i = i + 1;								# 과목 번호에 해당하는 j 1증가
	end while;										# while 반복문 종료
end $$												# 프로시저와 delimiter 종료르 명시

call input_answer(3, 20);							# 20문제씩 3과목에 해당하는 임의의 답안지 answer테이블에 저장하는 프로시저 호출
select * from answer;								# answer테이블 확인


-- drop procedure if exists input_testing;
-- delimiter $$
-- create procedure input_testing(size_subject integer, size_student integer)
-- begin
-- 	declare i, j integer;
--     declare k integer;
--     declare name_a varchar(20);
-- 	declare name_s varchar(20);
--     declare num_r double;
--     
--     delete from testing;
--     set i = 1;
-- 	while i <= size_subject do
--         set j = 1;
--         while j <= size_student do
-- 			set name_s = concat("홍길", cast(j as char(4)));
-- 			insert into testing(subjectid, stu_name, stu_id) value(i, name_s, j);
--             set k = 1;
-- 			while k < 10 do
-- 				set name_a = concat("a0", cast(k as char(4)));
-- 				set num_r = rand()*4+1;
-- 				set @query_txt = concat('update testing set ', name_a, ' =  ', num_r, 'where subjectid = ', i, ' and stu_id = ', j);
-- 				prepare stmt from @query_txt;
-- 				execute stmt;
-- 				set k = k + 1;
-- 			end while;
-- 			while k < 21 do
-- 				set name_a = concat("a", cast(k as char(4)));
-- 				set num_r = rand()*4+1;				# 리펙토링 소지가 다 분하다. 어차피 stmt쓰는거 다 완성 시켜서 한번에 insert 하면되는데.
-- 				set @query_txt = concat('update testing set ', name_a, ' =  ', num_r, 'where subjectid = ', i, ' and stu_id = ', j);
-- 				prepare stmt from @query_txt;
-- 				execute stmt;
-- 				set k = k + 1;
-- 			end while;
--             set j = j + 1;
--         end while;
--         set i = i + 1;
-- 	end while;
-- end $$

-- call input_testing(3, 1000);
-- select * from testing;


# 리펙토링 된 버전
# 2 - 1000명의 학생 시험 데이터 입력하기
drop procedure if exists input_testing;		# input_testing프로시저 존재하면 삭제
delimiter $$
create procedure input_testing(size_subject integer, size_student integer, size_p integer)	# 과목 수와 학생수와 문제수를 파라미터로 받는 input_testing 프로시저 생성
begin
	declare i, j integer;					# integer형 변수 i, j 선언
    declare k integer;						# integer형 변수 k 선언
    declare num_r integer;					# 문제의 답이 될 임의의 수가 저장될 변수 선언
    
    delete from testing where stu_id > 0;
    set i = 1;
	while i <= size_subject do		# 과묵수 3 만큼 while문 반복
        set j = 1;					# 학생 번호를 나타내는 j 1로 초기화
        while j <= size_student do		# 학생수 1000 만큼 while문 반복
            set k = 0;					# 문제 번호를 나타내는 j 1로 초기화
            set @query_txt = concat('insert into testing values(', cast(i as char(4)), ',', concat('"홍길', cast(j as char(4))), '",', cast(j as char(4)), ',');		# insert쿼리문의 value 기준 앞의 내용을 @query_txt에 저장
			while k < size_p do				# 문제수 20 만큼 while문 반복
				set num_r =  rand()*4 + 1;										# 임의의 숫자 저장
				set @query_txt = concat(@query_txt, cast(num_r as char(4)));	# 문제 수 20만큼 임의의 값을 쿼리문에 이어서 저장
                if( k != size_p-1) then											# 마지막 문제의 값을 빼고
					set @query_txt = concat(@query_txt, ",");					#   ,를 붙인다.
                end if;															#   조건문 종료 명시
                set k = k + 1;				# 문제 번호를 나타내는 k를 1증가
			end while;						# while 반복문 종료
            set @query_txt = concat(@query_txt, ');');		# @query_txt에 저장된 쿼리문 마무리 채우기
			prepare stmt from @query_txt;					# @query_txt에 저장될 쿼리문을 statement에 저장
			execute stmt;									# statement에 저장된 쿼리문을 실행
            set j = j + 1;					# 학생 번호를 나타내는 j를 1증가
        end while;							# while 반복문 종료
        set i = i + 1;						# 학생 번호를 나타내는 i를 1증가
	end while;								# while 반복문 종료
end $$										# 프로시저와 delimiter 종료 명시

call input_testing(3, 1000, 20);			# 3과목, 1000명, 20문제의 데이터를 testing테이블에 저장하는 프로시저 호출
select * from testing;						# testing 테이블 확인
select count(*) from testing;				# testing 테이블의 총 레코드 수 확인


# -----------------------------------------------------
# 3
# 채점하는 프로시저
drop procedure if exists insert_result;
delimiter $$
create procedure insert_result(p_size integer)
begin
	declare i integer default 1;
    declare field_name varchar(200);
    
    # 채점
    set @query_txt = concat('insert into scoring(subjectid, stu_name, stu_id,');
    
    # 필드명 20개
     while i < p_size+1 do
		set @query_txt = concat(@query_txt, " a", cast(LPAD(i, 2, 0) as char(4)));
        if i != p_size then
			set @query_txt = concat(@query_txt, ",");
        end if;
		set i = i + 1;
    end while;    
    
    set @query_txt = concat(@query_txt, ') select subjectid, stu_name, stu_id, ');

	# 필드값 20개
	set i = 1;
    while i < p_size+1 do
		set field_name = concat("a", cast(LPAD(i, 2, 0) as char(4)));
		set @query_txt = concat(@query_txt, 'case when (select i2.',
								field_name, ' from answer as i2 where i2.subjectid=t.subjectid) = (select i1.',
								field_name, ' from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1 else 0 end as tf');
		if i != p_size then
			set @query_txt = concat(@query_txt, ",");
        end if;
		set i = i + 1;
    end while;
    
    # 쿼리 실행
	set @query_txt = concat(@query_txt, " from testing as t;");
	prepare stmt from @query_txt;
	execute stmt;   
end $$

call insert_result(20);

select * from scoring;
select * from answer;
select * from testing;

delete from scoring where stu_id > 0;

-- insert into scoring(subjectid, stu_name, stu_id, a01, a02, a03, a04, a05, ... , a20)
-- 	 select subjectid, stu_name, stu_id,
-- 		case
-- 			when (select i2.a01 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a01 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a01,
-- 		case
-- 			when (select i2.a02 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a02 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a02,
-- 		case
-- 			when (select i2.a03 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a03 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a03,
-- 		case
-- 			when (select i2.a04 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a04 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a04,
-- 		case
-- 			when (select i2.a05 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a05 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a05,
--		...
-- 		...    
-- 		case
-- 			when (select i2.a20 from answer as i2 where i2.subjectid=t.subjectid) = (select i1.a20 from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1
-- 			else 0
-- 			end as a20,
-- 		from testing as t;


# 3 - upgrade [점수 집계 추가]
# 채점하고 결과 집계하는 프로시저
drop procedure if exists insert_result;
delimiter $$
create procedure insert_result(p_size integer)
begin
	declare i integer default 1;
    declare field_name varchar(200);
    
    delete from scoring where stu_id > 0;
    
    # 채점
    set @query_txt = concat('insert into scoring(subjectid, stu_name, stu_id,');
    
    # 필드명 20개
     while i < p_size+1 do
		set @query_txt = concat(@query_txt, " a", cast(LPAD(i, 2, 0) as char(4)));
        if i != p_size then
			set @query_txt = concat(@query_txt, ",");
        end if;
		set i = i + 1;
    end while;    
    
    set @query_txt = concat(@query_txt, ') select subjectid, stu_name, stu_id, ');

	# 필드값 20개
	set i = 1;
    while i < p_size+1 do
		set field_name = concat("a", cast(LPAD(i, 2, 0) as char(4)));
		set @query_txt = concat(@query_txt, 'case when (select i2.',
								field_name, ' from answer as i2 where i2.subjectid=t.subjectid) = (select i1.',
								field_name, ' from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1 else 0 end as tf');
		if i != p_size then
			set @query_txt = concat(@query_txt, ",");
        end if;
		set i = i + 1;
    end while;
    
    # 채점 쿼리 쿼리 실행
	set @query_txt = concat(@query_txt, " from testing as t;");
	prepare stmt from @query_txt;
	execute stmt;   
    
	# 점수 매기기
    set i = 1;
    set @query_txt = concat('update scoring as a, (select subjectid, stu_id, ');
    # 필드명 20개
     while i < p_size+1 do
		set @query_txt = concat(@query_txt, " a", cast(LPAD(i, 2, 0) as char(4)));
        if i != p_size then
			set @query_txt = concat(@query_txt, "+");
        end if;
		set i = i + 1;
    end while;    
    
    # 점수 집계 쿼리 실행
	set @query_txt = concat(@query_txt, 
						" as sum from scoring) as b set a.score=b.sum*5 where a.subjectid=b.subjectid and a.stu_id=b.stu_id;");
	prepare stmt from @query_txt;
	execute stmt;   
end $$

call insert_result(20);

select * from scoring;
select * from answer;
select * from testing;

delete from scoring where stu_id > 0;


# 4
# 3 upgrate의 upgrade [리포트 테이블에 insert 추가]

# 3 & 4. 채점하여 scoring테이블 문제별 정답유무 저장 & score계산하여 저장 / reporting테이블에 결과 저장
drop procedure if exists insert_result;		# insert_result프로시저 존재하면 삭제
delimiter $$								
create procedure insert_result(size_a integer, size_s integer)		#문제 수와 학생 수를 파라미터로 받는 insert_result프로시저 생성
begin
	declare i integer default 1;		# 문제 번호에 해당하는 변수 1로 선언
    declare field_name varchar(200);	# 문제 필드명이 저장될 변수 varchar타입으로 선언
    
    delete from scoring where stu_id > 0;	# scoring테이블의 기존 레코드 모두 삭제
    
    ## [채점하기]
    set @query_txt = concat('insert into scoring(subjectid, stu_name, stu_id,');	# insert문에 해당하는 앞부분 쿼리문 @query_txt에 저장
    # 필드명 20개
     while i < size_a+1 do			# 학생수 만큼 반복
		set field_name = concat(" a", cast(LPAD(i, 2, 0) as char(4)));	# 문제 필드명 a00 형태 완성해서 field_name 변수에 저장
		set @query_txt = concat(@query_txt, field_name);				# field_name을 쿼리문 @query_txt에 이어 저장
        if i != size_a then												# 마지막 문제 번호가 아니면
			set @query_txt = concat(@query_txt, ",");					#    "," 쿼리에 이어 저장
        end if;	
		set i = i + 1;													# 다음 반복문을 위해 문제 번호에 해당하는 i 증가
    end while;    														# while반복문 종료 -> 여기까지 하면 @query_txt에 insert문의 필드명까지 저장되어 있다.
    set @query_txt = concat(@query_txt, ') select subjectid, stu_name, stu_id, ');	# insert될 select문에 해당하는 앞부분 @query_txt에 이어 저장
    
	# 필드값 20개
	set i = 1;					# 이어질 반복문을 위해 i를 1로 초기화
    while i < size_a+1 do		# 문제수 20만큼 반복
		set field_name = concat("a", cast(LPAD(i, 2, 0) as char(4)));	# 문제 필드명 a00 형태 완성해서 field_name 변수에 저장
		set @query_txt = concat(@query_txt, 'case when (select i2.',	# case문 에 해당하는 쿼리문 @query_txt에 이어 저장
								field_name, ' from answer as i2 where i2.subjectid=t.subjectid) = (select i1.',
								field_name, ' from testing as i1 where i1.subjectid=t.subjectid and i1.stu_id=t.stu_id) then 1 else 0 end as tf');
		if i != size_a then								# 마지막 문제 번호가 아니면
			set @query_txt = concat(@query_txt, ",");	#    "," 쿼리에 이어 저장
        end if;
		set i = i + 1;									# 다음 반복문을 위해 문제 번호에 해당하는 i 증가
    end while;
    
    # 지금까지 저장된 채점 쿼리문 실행
	set @query_txt = concat(@query_txt, " from testing as t;");	# 나머지에 insert의 뒷부분에 해당하는 쿼리문 @query_txt에 이어 저장
	prepare stmt from @query_txt;						# 쿼리문 @query_txt를 statement에 저장
	execute stmt;   									# statement에 저장된 쿼리문 실행
    
    
	## [점수 매기기]
    set i = 1;						# 반복문을 위해 문제 번호에 해당하는 i를 1로 초기화
    set @query_txt = concat('update scoring as a, (select subjectid, stu_id, ');	# update를 쿼리문을 통해 null로 비어있는 score를 채우기 위한 쿼리문 앞 부분 작성하여 쿼리문 @query_txt에 저장
    # 필드명 20개
     while i < size_a+1 do			# 문제 수 20 만큼 반복
		set @query_txt = concat(@query_txt, " a", cast(LPAD(i, 2, 0) as char(4)));	# @query_txt에 문제의 필드명 이어서 저장
        if i != size_a then											# 마지막 문제 번호가 아니면
			set @query_txt = concat(@query_txt, "+");				#   "+"를 @query_txt에 이어서 저장
        end if;
		set i = i + 1;				# 다음 반복문을 위해 문제 번호에 해당하는 i 증가
    end while;    
    
    # 지금까지 저장된 점수 집계 쿼리문 실행
	set @query_txt = concat(@query_txt, 							# update문의 뒷 부분에 해당하는 내용을 @query_txt에 저장
						" as sum from scoring) as b set a.score=b.sum*5 where a.subjectid=b.subjectid and a.stu_id=b.stu_id;");
	prepare stmt from @query_txt;	# 쿼리문 @query_txt를 statement에 저장
	execute stmt;   				# statement에 저장된 쿼리문 실행
    
    
    ## [리포트 테이블에 입력하기]
    delete from report_table where stu_id > 0;						# 기존에 report_table 테이블에 존재하는 모든 레코드 삭제
	insert into report_table(stu_name, stu_id, kor)					# report_table테이블에 학생명과 학번, 국어 성적을 아래 select문에서 조회 된 레코드로 입력
		select stu_name, stu_id, score from scoring where subjectid=1;			# 모든 학생의 학생명과 학번과 1번 과목인 국어 성적을 조회
    update report_table, scoring set eng=scoring.score where scoring.subjectid=2 and report_table.stu_id=scoring.stu_id;	# scoring테이블의 영어 성적을 report_table테이블에 해당 학번과 과목에  해당하는 레코드에 업데이트
	update report_table, scoring set mat=scoring.score where scoring.subjectid=3 and report_table.stu_id=scoring.stu_id;	# scoring테이블의 수학 성적을 report_table테이블에 해당 학번과 과목에  해당하는 레코드에 업데이트
end $$								# 프로시저와 delimiter의 종료를 명시

call insert_result(20, 3);			# 채점하여 scoring테이블 문제별 정답유무 저장 & score계산하여 저장 / reporting테이블에 결과 저장하는 프로시저 호출



select * from scoring;

select * from answer;
select * from testing;
select * from report_table;

-- insert into report_table(stu_name, stu_id, kor)
-- 	select stu_name, stu_id, score from scoring where subjectid=1;
-- update report_table, scoring set eng=scoring.score where scoring.subjectid=2 and report_table.stu_id=scoring.stu_id;
-- update report_table, scoring set eng=scoring.score where scoring.subjectid=3 and report_table.stu_id=scoring.stu_id;

-- delete from report_table where stu_id > 0;



# 5 채점 리포트에 더하여 합계 편균 등수 보여주기
select
	*, (kor+eng+mat) as sum, (kor+eng+mat)/3 ave,											# report_table의 모든 필드와 합계와 평균 필드 추가
    (select count(*)+1 from report_table as i where (i.kor+i.eng+i.mat) > sum) as rank_sum	# 현재 레코드의 합계보다 큰 합계의 개수를 센 등수 필드 추가
    from report_table as o order by rank_sum;												# 모든 레코드에 대하여 조회하되 등수 오름차순으로 정렬
    
    
    
# 6 - 과목, 문항별 오답율 테이블
drop table if exists result_examples;		# rate_examples 테이블이 존재하면 삭제
create table result_examples(				# rate_examples 테이블 생성
	no_s int,					# 과목 번호 필드 추가
    no_p int,					# 문제 번호 필드 추가
    count int, 					# 정답자 수 필드 추가
    rate double,				# 문제 오답율 필드 추가
    primary key(no_s, no_p));	# pk로 과목번호와 문제 번호 설정
desc result_examples;			# rate_examples 테이블 필드 정보 조회


drop procedure if exists result_examples;	# result_examples테이블 존재시 삭제
delimiter $$
create procedure result_examples()			# result_examples 테이블 생성
begin										# 프로시저 시작을 명시
	declare i, j int default 1;				# 과목에 해당하는 i와 문제번호에 해당하는 j를 1로 초기화
			
    delete from result_examples where no_s > 0;	# 기존에 result_examples테이블에 존재하는 레코드 삭제
	while i <= 3 do		# 과목 수만큼 반복
		set j = 1;			# i에 대한 반복문을 위하여 j를 1로 초기화
		while j <= 20 do	# 문제 수만큼 반복
			set @query_txt = concat("insert into result_examples values(", cast(i as char(4)), ", ",		# 한과목, 한문제에 대한 오답율과 득점자수를 입력하는 쿼리문 @query_txt 쿼리에 저장
												cast(j as char(4)), ", (select count(", concat("a", cast(LPAD(j, 2, 0) as char(4))), ") from scoring where subjectid=", cast(i as char(4)), " and ",
                                                concat("a", cast(LPAD(j, 2, 0) as char(4))), "=1), (select count(", concat("a", cast(LPAD(j, 2, 0) as char(4))), ") from scoring where subjectid=", 
                                                cast(i as char(4)), " and ", concat("a", cast(LPAD(j, 2, 0) as char(4))), "=1) / 20);");
            prepare stmt from @query_txt;		# @query_txt에 저장된 쿼리문을 statement에 저장
            execute stmt;						# statement에 저장된 쿼리문 실행
            set j = j + 1;	# j에 대한 반복문을 위하여 j 증가
        end while;			# while 반복문 종료
        set i = i + 1;		# i에 대한 반복문을 위하여 i 증가
    end while;				# while 반복문 종료
end $$						# 프로시저와 delimiter 종료

call result_examples();		# 과목별, 문제별 정답율과 득점자수 입력 프로시저 호출
select * from result_examples; # result_examples 조회


insert into result_examples(subjectid,  as
select count(a01) from scoring where subjectid=1 and a01=1;
insert into result_examples values(, 1, (select count(a01) from scoring where subjectid=1 and a01=1), (select count(a01) from scoring where subjectid=1 and a01=1) / (select count(a01) from scoring where subjectid=1));
insert into result_examples values(1, 1, (select count(a01) from scoring where subjectid=1 and a01=1), (select count(a01) from scoring where subjectid=1 and a01=1) / (select count(a01) from scoring where subjectid=1));
insert into result_examples values(1, 2, (select count(a02) from scoring where subjectid=1 and a02=1), (select count(a01) from scoring where subjectid=1 and a02=2) / 20);
select subjectid, 1, count(a01), count(a01)/20 from from scoring where subjectid=1 and a01=1;

select * from scoring;
select * from result_examples;

    
    
    
    
    
# p25 - 리조트 예약
# 1 - 테이블 생성
drop table if exists reservation_resort;	# reservation_resort 테이블이 존재하면 삭제
create table reservation_resort(			# reservation_resort 테이블 생성
	date_checkin date,						# 예약된 체크인 날짜를 date타입의 필드로 추가
    no_room int,							# 방번호를 int타입의 필드로 추가
    name_reservation varchar(20),			# 예약자명을 varchar타입의 필드로 추가
    address varchar(100),					# 예약자 주소를 varchar타입의 필드로 추가
    number_phone varchar(20),				# 예약자 번호를 varchar타입의 필드로 추가
    name_deposit varchar(20),				# 입금자명을 varchar타입의 필드로 추가
    meno varchar(100),						# 예약 특이사항을 varchar타입의 필드로 추가
    date_reservation date,					# 예약일자를 date타입의 필드로 추가
    primary key(date_checkin, no_room));	# 체크인 날짜와 방번호를 pk로 설정
desc reservation_resort;					# reservation_resort 테이블의 필드 정보 조회

# 2- 임의의 데이터 넣기
delete from reservation_resort where no_room>0;
insert into reservation_resort values("2022-05-19", 1, "나연","서울시 동작구 상도1동 17-2", "010-1111-2222", "나연", "공기청전기 요청", now());
insert into reservation_resort values("2022-05-21", 3, "모모","서울시 동작구 상도1동 15-2", "010-1111-3333", "모모", "와인잔 요청", now());
insert into reservation_resort values("2022-05-22", 2, "사나","서울시 동작구 상도1동 13-2", "010-8889-2222", "사나", "고층 요청", now());
insert into reservation_resort values("2022-05-27", 3, "정연","서울시 동작구 상도1동 11-2", "010-2315-2342", "정연", "먼지 알레르지 주의", now());
insert into reservation_resort values("2022-05-30", 3, "정연","서울시 동작구 상도1동 71-2", "010-2315-2342", "정연", "조식 요청", now());
insert into reservation_resort values("2022-05-22", 1, "쯔위","서울시 동작구 상도1동 23-2", "010-2375-1292", "쯔위", "모닝콜 요청", now());
insert into reservation_resort values("2022-05-29", 2, "나연","서울시 동작구 상도1동 50-2", "010-1234-3562", "나연", "결혼 기념일 이벤트 요청", now());
insert into reservation_resort values("2022-06-11", 2, "채영","서울시 동작구 상도1동 11-2", "010-1789-5631", "채영", "엑스트라 베드 요청", now());
insert into reservation_resort values("2022-06-05", 1, "미나","서울시 동작구 상도1동 42-2", "010-2310-0853", "미나", "발렛파킹 요청", now());
insert into reservation_resort values("2022-06-01", 3, "다현","서울시 동작구 상도1동 87-2", "010-4311-6274", "다현", "공기청정기 요청", now());
insert into reservation_resort values("2022-06-01", 1, "채영","서울시 동작구 상도3동 87-2", "010-1356-1235", "채영", "비데 요청", now());

select * from reservation_resort;

# 예약 현황 테이블 생성
drop table if exists status_reservation;	# status_reservation 테이블 존재시 삭제
create table status_reservation(			# status_reservation 테이블 생성
	reserv_date date primary key,			# 날짜를 date타입의 필드로 추가하고 pk로 설정
    room1 varchar(20),						# 방1의 예약정보를 varchar의 필드로 추가
    room2 varchar(20),						# 방2의 예약정보를 varchar의 필드로 추가
    room3 varchar(20));						# 방3의 예약정보를 varchar의 필드로 추가
desc status_reservation;					# status_reservation 테이블의 필드 정보 조회


# 예약기록을 바탕으로 예약 현황 테이블에 집계하는 프로시저
drop procedure if exists update_status_reservation;	# update_status_reservation 프로시저 삭제
delimiter $$
create procedure update_status_reservation()		# update_status_reservation 프로시저 생성
begin
	declare date_now date default now();			# date타입의 변수에 지금 시간 정보로 초기화
    declare i int default 0;						# 오늘로 부터 미래의 날짜를 나타내는 일 수 i 0으로 초기화
    declare name1 varchar(20);						# 1번 방의 예약 정보가 저장될 변수 선언
    declare name2 varchar(20);						# 2번 방의 예약 정보가 저장될 변수 선언
    declare name3 varchar(20);						# 3번 방의 예약 정보가 저장될 변수 선언
    
    delete from status_reservation where reserv_date > date_sub(now(), interval 10 year);		# status_reservation 테이블에 저장된 모든 레코드 삭제
    
    while date_add(now(), interval i day) < date_add(now(), interval 1 month) do	# 오늘부터 1달 뒤의 날짜까지 i를 증가하며 반복
		set name1 = "";				# 1번 방의 예약 정보가 저장될 변수 공백을 초기화
		set name2 = ""; 			# 2번 방의 예약 정보가 저장될 변수 공백을 초기화
		set name3 = ""; 			# 3번 방의 예약 정보가 저장될 변수 공백을 초기화
        
		select name_reservation into name1 from reservation_resort where date_checkin=date_format(date_add(now(), interval i day), '%Y-%m-%d') and no_room=1;	# 오늘로부터 i일 후의 1번 방의 예약자명 조회
		select name_reservation into name2 from reservation_resort where date_checkin=date_format(date_add(now(), interval i day), '%Y-%m-%d') and no_room=2;	# 오늘로부터 i일 후의 2번 방의 예약자명 조회
		select name_reservation into name3 from reservation_resort where date_checkin=date_format(date_add(now(), interval i day), '%Y-%m-%d') and no_room=3;	# 오늘로부터 i일 후의 3번 방의 예약자명 조회
        
        # 1번방
        if name1 = "" then			# 조회한 1번 방의 예약자명이 없으면 
			insert into status_reservation(reserv_date, room1) values(date_format(date_add(now(), interval i day), '%Y-%m-%d'), "예약가능");		# 해당 날짜에 1번방은 예약가능으로 저장
		else						# 예약자명이 있다면 
			insert into status_reservation(reserv_date, room1) values(date_format(date_add(now(), interval i day), '%Y-%m-%d'), name1);			# 해당 날짜에 1번방은 조회된 예약자명 저장
        end if;						# 예약자명이 있다면
        # 2번방
		if name2 = "" then			# 조회한 2번 방의 예약자명이 없으면 
			update status_reservation set room2="예약가능" where reserv_date = date_format(date_add(now(), interval i day), '%Y-%m-%d');			# 해당 날짜에 2번방은 예약가능으로 저장
		else
			update status_reservation set room2=name2 where reserv_date = date_format(date_add(now(), interval i day), '%Y-%m-%d');				# 해당 날짜에 2번방은 조회된 예약자명 저장
        end if;						# 예약자명이 있다면
        # 3번방
		if name3 = "" then			# 조회한 3번 방의 예약자명이 없으면 
			update status_reservation set room3="예약가능" where reserv_date = date_format(date_add(now(), interval i day), '%Y-%m-%d');			# 해당 날짜에 3번방은 예약가능으로 저장
		else
			update status_reservation set room3=name3 where reserv_date = date_format(date_add(now(), interval i day), '%Y-%m-%d');				# 해당 날짜에 3번방은 조회된 예약자명 저장
        end if;
        
        set i = i + 1;				# 다음 반복문을 위해서 오늘로 부터 이후 날짜 일 수에 해당하는 i 증가
    end while;						# while 반복문 종료 명시
end $$								# 프로시저와 delimiter 종료 명시

call update_status_reservation();	# 예약기록을 바탕으로 예약 현황 테이블에 집계하는 프로시저 호출
select * from status_reservation;	# 프로시저로 업데이트 된 status_reservation 테이블 확인



select name_reservation into @name from reservation_resort where date_checkin=date_format(2022-06-01, '%Y-%m-%d');
select @name;


select * from examtable;
desc examtable;