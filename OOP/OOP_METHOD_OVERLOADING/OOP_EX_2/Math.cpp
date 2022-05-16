#include "Math.h"

int Math::Add(int x, int y)
{
	return (x + y);
}

int Math::Add(int x, int y, int z)
{
	return (x + y + z);
}

int Math::Add(double x, double y)
{
	return int(x + y);
}

int Math::Add(double x, double y, double z)
{
	return int(x + y + z);
}

int Math::Mul(int x, int y)
{
	return (x * y);
}

int Math::Mul(int x, int y, int z)
{
	return (x * y * z);
}

int Math::Mul(double x, double y)
{
	return int(x * y);
}

int Math::Mul(double x, double y, double z)
{
	return int(x * y * z);
}

int Math::Add(int count, ...)
{
	int sum = 0;
	va_list vl;
	va_start(vl, count);
	for (int i = 0; i < count; i++)
	{
		union Numbers
		{
			short a;
			int b;
			double c;
		}number;
		switch (i)
		{
		case 'a':
			number.a = va_arg(vl, short);
			sum = sum + number.a;
			break;

		case 'b':
			number.b = va_arg(vl, int);
			sum = sum + number.b;
			break;
		case 'c':
			number.c = va_arg(vl, double);
			sum = sum + int(number.c);
			break;
		default:
			break;
		}
	}
	va_end(vl);
	return sum;
}
