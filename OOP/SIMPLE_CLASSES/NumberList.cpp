#include "NumberList.h"

void swap(int x, int y)
{
	int aux;
	aux = x;
	x = y;
	y = aux;
}

void NumberList::Init()
{
	count = 0;
}
bool NumberList::Add(int x)
{
	if (count >= 10)
		return false;
	else
		return true;
}
void NumberList::Sort()
{
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 10 - i - 1; j++)
			if (numbers[j] > numbers[j + 1])
				swap(numbers[j], numbers[j + 1]);
}
void NumberList::Print()
{
	for (int i = 0; i < 10; i++)
	{
		printf("%d", numbers[i]);
		printf("%s", ", ");
	}
	printf("%d\n");
}