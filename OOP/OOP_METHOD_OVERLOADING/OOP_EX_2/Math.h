#pragma once
#include <stdio.h>
#include <stdarg.h>
#include <string>

class Math
{
public:
	static int Add(int, int); // Addition for 2 integer values
	static int Add(int, int, int); // Addition for 3 integer values
	static int Add(double, double); // Addition for 2 double values and returns integer value
	static int Add(double, double, double); // Addition for 3 double values and returns integer value
	static int Mul(int, int); // Multiplication for 2 integer values
	static int Mul(int, int, int); // Multiplication for 3 integer values
	static int Mul(double, double); // Multiplication for 2 double values and returns integer value
	static int Mul(double, double, double); // Multiplication for 3 double values and returns integer value
	static int Add(int count, ...); // Sums up a list of integers, double, short and returns integer value
};