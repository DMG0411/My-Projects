#include <iostream>
#include "Student.h"

void Student::NameSet(std::string x)
{
	name = x;
}
void Student::MathSet(int x)
{
	math_grade = x;
}
void Student::EngSet(int x)
{
	eng_grade = x;
}
void Student::HstSet(int x)
{
	hst_grade = x;
}
float Student::AvgGrade(int math_grade, int eng_grade, int hst_grade)
{
	avg = (math_grade + eng_grade + hst_grade) / 3.0;
	return avg;
}