#include <string>		// string클래스 
#include <iostream>		// cout, cin 
  
// c++에서 string클래스 등장. 
  
using namespace std;	// string 클래스는 std네임스페이스에 속해있으므로 참조해야 한다. 

string a()
{
	string a="abc";
	
	cout << a << '\n';
	
	return a;	
} 

int main()
{
	string str = a()+ "abc"; // 반환형으로 string사용 가능
		
	
	//printf("%s",str); printf로 std:string 데이터 타입 출력 불가. 
	cout << str << '\n';
	
	return 0;
} 

