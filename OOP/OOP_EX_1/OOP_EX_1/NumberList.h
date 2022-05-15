#pragma once
#include <cstdio>

void swap(int x, int y)
{
	int aux;
	aux = x;
	x = y;
	y = aux;
}

class NumberList
{
	int numbers[10];
	int count;
public:
	void Init(); // count will be 0
	bool Add(int x); // adds X to the number list and increase the data member count
					 // if count is bigger or equal to 10, the function will return false
	void Sort(); // will sort the numbers of the vector
	void Print(); // will print the current vector
};

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
                 printf("CTOR: %d");
}
