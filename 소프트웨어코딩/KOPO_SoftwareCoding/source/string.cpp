#include <string>		// stringŬ���� 
#include <iostream>		// cout, cin 
  
// c++���� stringŬ���� ����. 
  
using namespace std;	// string Ŭ������ std���ӽ����̽��� ���������Ƿ� �����ؾ� �Ѵ�. 

string a()
{
	string a="abc";
	
	cout << a << '\n';
	
	return a;	
} 

int main()
{
	string str = a()+ "abc"; // ��ȯ������ string��� ����
		
	
	//printf("%s",str); printf�� std:string ������ Ÿ�� ��� �Ұ�. 
	cout << str << '\n';
	
	return 0;
} 

