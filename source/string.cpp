#include <stdio.h>

char *string() 
{
	char *a="Hello World!"; 
	a="no";
	return a;
}

int main()
{
	char *s;
	s=string();
	printf("%s", s);
}

