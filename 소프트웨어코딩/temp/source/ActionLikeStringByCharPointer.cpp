#include <stdio.h>

// c���� string����� �����ϴ� ��� 

char *string() 
{
	char *a="Hello World!"; 
	a="no";
	printf("%s\n", a);
	return a;
}

int main()
{
	char *s;
	s=string();
	printf("%s\n", s);
}

