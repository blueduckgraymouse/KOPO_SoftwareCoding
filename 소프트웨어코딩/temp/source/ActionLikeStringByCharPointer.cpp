#include <stdio.h>

// c에서 string기능을 구현하는 방법 

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

