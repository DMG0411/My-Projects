#include "Student.h"

// We make 5 functions to compare the name, grades and average grades of two students 
// If the first is bigger than the second one the functions will return 1, -1 if the second student is bigger
// Or 0 if both students are equal

int NameCmp(std::string x, std::string y)
{
	if (x > y)
	{
		printf("%d\n", 1);
		return 1;
	}
	else if (x < y)
		return -1;
	printf("%d\n", 0);
	return 0;
}
int MathCmp(int x, int y)
{
	if (x > y)
		return 1;
	else if (x < y)
		return -1;
	return 0;
}
int EngCmp(int x, int y)
{
	if (x > y)
		return 1;
	else if (x < y)
		return -1;
	return 0;
}
int HstCmp(int x, int y)
{
	if (x > y)
		return 1;
	else if (x < y)
		return -1;
	return 0;
}
int AvgCmp(float x, float y)
{
	if (x > y)
		return 1;
	else if (x < y)
		return -1;
	return 0;
}
void main()
{
	Student s1;
	Student s2;
	s1.NameSet("Mike");
	s2.NameSet("Todd");
	s1.EngSet(10);
	s2.EngSet(9);
	s1.MathSet(8);
	s2.MathSet(8);
	s1.HstSet(7);
	s2.HstSet(5);
	s1.AvgGrade(s1.eng_grade, s1.math_grade, s1.hst_grade);
	s2.AvgGrade(s2.eng_grade, s2.math_grade, s2.hst_grade);

	NameCmp(s1.name, s2.name);
	EngCmp(s1.eng_grade, s2.eng_grade);
	MathCmp(s1.math_grade, s2.math_grade);
	HstCmp(s1.hst_grade, s2.hst_grade);
	AvgCmp(s1.avg, s2.avg);
}