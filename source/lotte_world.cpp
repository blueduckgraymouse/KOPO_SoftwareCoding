#include <stdio.h>

/*
	���ǿ� ���� �Ե����� ����� ���� ��� ���α׷�
	
	����1 - �̿�ü�(����) 
		�����̿�� / ��ũ�̿� 
	
	����2 - �̿�ð�(����) 
		���ϱ�(1Day) / ���ı�(After4)
	
	����3 - ����(�ֹι�ȣ �Է� )
		� : �� 19�� �̻� ~ �� 64�� ���� 
		û�ҳ� : �� 13�� �̻� ~ �� 18�� ���� 
		��� : 36�����̻� ~ �� 12�� ����
				 �� 65�� �̻�	
		���̺� : 0~12���� �̸�(��ũ,���� ���̽ü� ����)
				 12���� �̻�~36���� �̸�(��ũ ���� ����, ���� ���̽ü� ����)
				 ��, 12~36������ �ش��ϸ� ���� ���̽ü� �̿��� ���� �ٽ� ����� ��.
	
	����4 - ����(���� �Է�) 
	
	����5 - ������(��� ��� ����, ����)
		����
		����� ��� : 50% ����, ���� 1�α��� 
		���������� : 50% ����, ���� 1�α��� 
		�ް��庴 : 49% ����, ���� 1�α��� 
		�ӻ�� : 50% ���� , ���θ�
		�ٵ��� �ູī�� : 30%����, ī�忡 ��õ� ������
*/

int main()
{
	// ���� 
	const int Package_Day_Adult_Price = 59000;
	const int Package_Day_Youth_Price = 52000;
	const int Package_Day_Child_Price = 47000;
	const int Package_Day_Baby_Price = 15000;
	
	const int Package_After4_Adult_Price = 48000;
	const int Package_After4_Youth_Price = 42000;
	const int Package_After4_Child_Price = 36000;
	const int Package_After4_Baby_Price = 15000;
	
	const int OnlyPark_Day_Adult_Price = 56000;
	const int OnlyPark_Day_Youth_Price = 50000;
	const int OnlyPark_Day_Child_Price = 46000;
	const int OnlyPark_Day_Baby_Price = 15000;
	
	const int OnlyPark_After4_Adult_Price = 45000;
	const int OnlyPark_After4_Youth_Price = 40000;
	const int OnlyPark_After4_Child_Price = 35000;
	const int OnlyPark_After4_Baby_Price = 15000;

	// ���� 
	const int Adult_Max_Age = 64;
	const int Adult_Min_Age = 19;
	const int Youth_Max_Age = 18;
	const int Youth_Min_Age = 13;
	const int Child_Max_Age = 12;
	const int Child_Min_Month = 36;
	const int Baby1_Max_Month = 35; 
	const int Baby1_Min_Month = 12;
	const int Baby2_Max_Month = 11;
	const int Older_Min_Age = 65;
	
	// ������
	const int Disabled_DiscountRate = 0.5;
	const int NationalMerit_DiscountRate = 0.5;
	const int Soldier_DiscountRate = 0.5;
	const int Pregnant_DiscountRate = 0.5;
	const int MultipleChildren_DiscountRate = 0.3;


	return 0;
}
