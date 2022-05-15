#pragma once
#include <string>

class Student
{
	
public:
	std::string name;
	int math_grade;
	int eng_grade;
	int hst_grade;
	float avg;
	void NameSet(std::string x); // Gets and sets the name of the student
	void MathSet(int x); // Gets and sets the grade on Math
	void EngSet(int x);  // Gets and sets the grade on English
	void HstSet(int x);  // Gets and sets the grade on History
	float AvgGrade(int x, int y, int z); // Gets and returns the average grade
};
void Student::NameSet(std::string x)
	{
		name = x;
	}
void Student:: MathSet(int x)
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