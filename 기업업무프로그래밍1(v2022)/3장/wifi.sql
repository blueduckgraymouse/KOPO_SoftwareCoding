use kopoctc;

SET GLOBAL log_bin_trust_function_creators = 1;
# 융기원으로 부터 거리는 계산하는 함수
drop function if exists f_dis;		# f_dis 함수 존재하면 삭제
delimiter $$
create function f_dis(lat double, lon double) returns double 	# double타입의 위도와 경도를 파리머톨 받아 융기원으로부터 거리를 double으로 반환하는 프로시저 생성
begin								# 프로시저 내용의 시작을 알림
	declare dis double;							# 거리가 저장될 변수 선언
	declare lat_now double default 37.3860521;	# 융기원의 위도 변수로 초기화
	declare lon_now double default 127.1214038; # 융기원의 경도 변수로 초기화
    
    set dis = (6371*acos(cos(radians(lat_now))*cos(radians(lat))*cos(radians(lon)-radians(lon_now))+sin(radians(lat_now))*sin(radians(lat)))); # 위도와 경도를 이용한 거리값 계산
return dis;			# 거리값 반환
end $$				# 프로시저와 delimiter의 끝을 알림


# 무료 와이파이 테이블의 주요 필드와 융기원으로부터의 거리 필드를 갖는 테이블을 생성하는 프로시저
drop procedure if exists print_freewifi;		# print_freewifi 프로시저가 존재하면 삭제
delimiter $$
create procedure print_freewifi(no_page integer, size_page integer)		# integer타입으로 페이지 번호와 사이즈를 파라미터로 받아 list_freewifi 테이블을 생성하는 프로시저 생성
begin									# 프로지서 내용의 시작을 알림
	declare size_tt integer;			# freewifi 테이블의 총 레코드 수가 저장될 변수 선언
    declare no_max_pg integer;			# 마지막 페이지 번호가 저장될 변수 선언 , 파라미터에 마지막 성적 페이지 번호보다 큰 값이 들어왔는지 확인 하기 위해 필요. 
    declare no_start integer;			# limit에서 조회 시작할 인덱스 번호를 나타내는 변수 선언
    declare lat_read double;			# freewifi 테이블에서 읽은 위도의 값을 저장할 변수 선언
    declare lon_read double;			# freewifi 테이블에서 읽은 경도의 값을 저장할 변수 선언
    
    # 페이징 처리
    select count(*) into size_tt from freewifi;		# freewifi 테이블의 총 개수 조회
    set no_max_pg = size_tt / size_page;			# 마지막 페이지 번호 계산
    if no_page < 1 then								# 파라미터로 들어온 페이지 번호가 1보다 작으면
		set no_page = 1;							# 	1 페이지로 조회하기 위해 no_page 변수에 0로 저장
	elseif no_page>no_max_pg then					# 파라미터로 들어온 페이지 번호가 마지막 페이지 번호보다 크면
		set no_page = no_max_pg;					# 	마지막 페이지 조회하기 위해 no_page 변수 no_max_pg로 저장
	end if;
	set no_page = no_page - 1;						# no_page는 시작 인덱스 계산에 사용되므로 조회하고자 하는 페이지에 -1한 값이 사용되야한다. 위의 if와 elseif에서 고려하여 대입한 것.
    set no_start = no_page * size_page;				# 현재 페이지 현황 정보 조회의 limit에서 사용될 조회 시작 인덱스 번호 계산 <= (limit no_start, no_page)
	
    # 현재 페이지 데이터만 조회& 거리 계산해서 테이블 생성
	drop table if exists list_freewifi;				# list_freewifi 테이블 존재하면 삭제
	create table list_freewifi as       			# 현재 페이지의 무료 와이파이 목록만 저장될 list_freewifi 테이블 생성
		select @rownum:=@rownum+1 as no, place_addr_land, latitude, longitude,
        f_dis(latitude, longitude) as distance_km	# 위도와 경도로 f_dis 함수 호출하여 계산된 거리값 필드 추가
        from freewifi WHERE (@rownum:=no_start)=no_start limit no_start , size_page;	# 현재 페이지에 한하여 조회
end $$					# 프로시저와 delimiter의 끝을 명시

call print_freewifi(20,30);				# 25개의 레코드를 기준으로 헀을 때 5페이지에 해당하는 데이터를 테이블로 생성하는 프로시저 호출
select * from list_freewifi;			# 생성된 테이블 확인
